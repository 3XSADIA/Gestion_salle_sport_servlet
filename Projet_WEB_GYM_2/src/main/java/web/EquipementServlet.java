package web;

import java.io.IOException;
import java.sql.SQLException;

import dao.EquipementDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Equipement;

/**
 * Servlet implementation class EquipementServlet
 */
@WebServlet("/EquipementServlet")
public class EquipementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EquipementDao dao;

	public EquipementServlet() throws ClassNotFoundException {
		dao = new EquipementDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String forward = "";
		if (action == null) {
			action = "default";
		}
		switch (action) {
		case "delete":
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				dao.deleteEquipement(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forward = "viewEquipements.jsp";
			RequestDispatcher del = request.getRequestDispatcher("viewEquipements.jsp");
			try {
				request.setAttribute("equipements", dao.getAllEquipements());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			del.forward(request, response);

			break;

		case "edit":
			forward = "addEquipement.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addEquipement.jsp");

			id = Integer.parseInt(request.getParameter("id"));
			try {
				Equipement eq = dao.getEquipementById(id);
				request.setAttribute("equipement", eq);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			edit.forward(request, response);

			break;

		default:
			RequestDispatcher view = request.getRequestDispatcher("viewEquipements.jsp");
			try {
				request.setAttribute("equipements", dao.getAllEquipements());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.forward(request, response);
			break;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Equipement eq = new Equipement();
		String nom = request.getParameter("nom");
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		eq.setNom(nom);
		eq.setQuantite(quantite);

		String id = (request.getParameter("id"));
		if (id == null || id.isEmpty()) {
			try {
				dao.addEquipement(eq);
				RequestDispatcher list_ = request.getRequestDispatcher("viewEquipements.jsp");
				request.setAttribute("equipements", dao.getAllEquipements());
				list_.forward(request, response);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			eq.setId(Integer.parseInt(id));
			try {
				EquipementDao.updateEquipement(eq);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher list = request.getRequestDispatcher("viewEquipements.jsp");
			try {
				request.setAttribute("equipements", dao.getAllEquipements());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.forward(request, response);
		}

	}

}
