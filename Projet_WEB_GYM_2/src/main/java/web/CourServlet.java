package web;

import java.io.IOException;
import java.sql.SQLException;

import dao.CoursDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cours;

/**
 * Servlet implementation class CourServlet
 */
@WebServlet("/CourServlet")
public class CourServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CoursDao dao;

	public CourServlet() throws ClassNotFoundException {
		dao = new CoursDao();
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
				dao.deleteCours(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forward = "viewCours.jsp";
			RequestDispatcher del = request.getRequestDispatcher("viewCours.jsp");
			try {
				request.setAttribute("cours", dao.getAllCours());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			del.forward(request, response);

			break;

		case "edit":
			forward = "addCours.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addCours.jsp");

			id = Integer.parseInt(request.getParameter("id"));
			try {
				Cours eq = dao.getCoursById(id);
				request.setAttribute("cours", eq);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			edit.forward(request, response);

			break;

		default:
			RequestDispatcher view = request.getRequestDispatcher("viewCours.jsp");
			try {
				request.setAttribute("cours", dao.getAllCours());
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
		Cours eq = new Cours();
		String label = request.getParameter("label");
		String equipements = (request.getParameter("equipements"));
		eq.setLabel(label);
		eq.setEquipements(equipements);

		String id = (request.getParameter("id"));
		if (id == null || id.isEmpty()) {
			try {
				dao.addCours(eq);
				RequestDispatcher list_ = request.getRequestDispatcher("viewCours.jsp");
				request.setAttribute("cours", dao.getAllCours());
				list_.forward(request, response);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			eq.setId(Integer.parseInt(id));
			try {
				CoursDao.updateCours(eq);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher list = request.getRequestDispatcher("viewCours.jsp");
			try {
				request.setAttribute("cours", dao.getAllCours());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.forward(request, response);
		}

	}

}
