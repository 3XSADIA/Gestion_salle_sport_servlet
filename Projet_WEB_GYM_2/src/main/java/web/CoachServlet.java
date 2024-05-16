package web;

import java.io.IOException;
import java.sql.SQLException;

import dao.CoachDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Coach;

/**
 * Servlet implementation class CoachServlet
 */
@WebServlet("/CoachServlet")
public class CoachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CoachDao dao;

	public CoachServlet() throws ClassNotFoundException {
		dao = new CoachDao();
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
				dao.deleteCoach(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forward = "viewCoachs.jsp";
			RequestDispatcher del = request.getRequestDispatcher("viewCoachs.jsp");
			try {
				request.setAttribute("coachs", dao.getAllCoachs());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			del.forward(request, response);

			break;

		case "edit":
			forward = "addCoach.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addCoach.jsp");

			id = Integer.parseInt(request.getParameter("id"));
			try {
				Coach eq = dao.getCoachById(id);
				request.setAttribute("coach", eq);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			edit.forward(request, response);

			break;

		default:
			RequestDispatcher view = request.getRequestDispatcher("viewCoachs.jsp");
			try {
				request.setAttribute("coachs", dao.getAllCoachs());
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
		Coach cl = new Coach();

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String tele = request.getParameter("num_telephone");
		String sal = request.getParameter("Salaire");
		String cours = request.getParameter("Cours");
		cl.setNom(nom);
		cl.setPrenom(prenom);
		cl.setCours(cours);
		cl.setSalaire(sal);
		cl.setNum_telephone(tele);

		String id = (request.getParameter("id"));
		if (id == null || id.isEmpty()) {
			try {
				dao.addCoach(cl);
				RequestDispatcher list_ = request.getRequestDispatcher("viewCoachs.jsp");
				request.setAttribute("coachs", dao.getAllCoachs());
				list_.forward(request, response);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			cl.setId(Integer.parseInt(id));
			try {
				CoachDao.updateCoach(cl);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher list = request.getRequestDispatcher("viewCoachs.jsp");
			try {
				request.setAttribute("coachs", dao.getAllCoachs());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.forward(request, response);
		}

	}

}
