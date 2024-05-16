package web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dao.ClientDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Client;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientDao dao;

	public ClientServlet() throws ClassNotFoundException {
		dao = new ClientDao();
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
				dao.deleteClient(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forward = "viewClients.jsp";
			RequestDispatcher del = request.getRequestDispatcher("viewClients.jsp");
			try {
				request.setAttribute("clients", dao.getAllClients());
				request.setAttribute("courses", dao.getAllCourses()); // Add this line
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			del.forward(request, response);

			break;

		case "edit":
			forward = "addClient.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addClient.jsp");

			id = Integer.parseInt(request.getParameter("id"));
			try {
				Client eq = dao.getClientById(id);
				request.setAttribute("client", eq);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			edit.forward(request, response);

			break;

		default:
			RequestDispatcher view = request.getRequestDispatcher("viewClients.jsp");
			try {
				request.setAttribute("clients", dao.getAllClients());
				request.setAttribute("courses", dao.getAllCourses()); // Add this line
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
		Client cl = new Client();

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String tele = request.getParameter("NumTelephone");
		String dpString = request.getParameter("dateInscription");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dpDte = null;
		try {
			dpDte = (Date) sdf.parse(dpString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String cours = request.getParameter("Cours");

		cl.setNom(nom);
		cl.setPrenom(prenom);
		cl.setCours(cours);
		cl.setDateInscription(dpDte);
		cl.setNumTelephone(tele);
		String id = (request.getParameter("id"));
		if (id == null || id.isEmpty()) {
			try {
				dao.addClient(cl);
				RequestDispatcher list_ = request.getRequestDispatcher("viewClients.jsp");
				request.setAttribute("clients", dao.getAllClients());
				list_.forward(request, response);
			} catch (SQLException | ClassNotFoundException e) {

			}
		}
	}
}