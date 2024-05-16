package web;

import java.io.IOException;
import java.sql.SQLException;

import dao.SeanceDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Seance;

/**
 * Servlet implementation class SeanceServlet
 */
@WebServlet("/SeanceServlet")
public class SeanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SeanceDao dao;

	public SeanceServlet() throws ClassNotFoundException {
		dao = new SeanceDao();
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
				dao.deleteSeance(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forward = "viewSeances.jsp";
			RequestDispatcher del = request.getRequestDispatcher("viewSeances.jsp");
			try {
				request.setAttribute("Seances", dao.getAllSeances());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			del.forward(request, response);

			break;

		case "edit":
			forward = "addSeance.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addSeance.jsp");

			id = Integer.parseInt(request.getParameter("id"));
			try {
				Seance eq = dao.getSeanceById(id);
				request.setAttribute("Seance", eq);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			edit.forward(request, response);

			break;

		default:
			RequestDispatcher view = request.getRequestDispatcher("viewSeances.jsp");
			try {
				request.setAttribute("Seances", dao.getAllSeances());
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
		Seance eq = new Seance();
		int id_coach = Integer.parseInt(request.getParameter("id_coach"));
		int id_cours = Integer.parseInt(request.getParameter("id_cours"));
		String horaire = request.getParameter("horaire");

		eq.setId_coach(id_coach);
		eq.setId_cours(id_cours);
		eq.setHoraire(horaire);

		String id = (request.getParameter("id"));
		if (id == null || id.isEmpty()) {
			try {
				dao.addSeance(eq);
				RequestDispatcher list_ = request.getRequestDispatcher("viewSeances.jsp");
				request.setAttribute("Seances", dao.getAllSeances());
				list_.forward(request, response);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			eq.setId(Integer.parseInt(id));
			try {
				SeanceDao.updateSeance(eq);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher list = request.getRequestDispatcher("viewSeances.jsp");
			try {
				request.setAttribute("Seances", dao.getAllSeances());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.forward(request, response);
		}

	}

}
