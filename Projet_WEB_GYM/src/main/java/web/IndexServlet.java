package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import dao.IndexDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Equipement;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IndexDao dao;

	public IndexServlet() throws ClassNotFoundException {
		dao = new IndexDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IndexDao indexDao = null;
		try {
			indexDao = new IndexDao();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			int nombreClients = indexDao.getClient();
			request.setAttribute("nombreClients", nombreClients);

			int nombreCoachs = indexDao.getCoach();
			request.setAttribute("nombreCoachs", nombreCoachs);

			int gains = indexDao.getGains();
			request.setAttribute("gains", gains);

			int nombreSeances = indexDao.getSeance();
			request.setAttribute("nombreSeances", nombreSeances);

			List<Equipement> equipmentList = indexDao.getEquipmentData();
			request.setAttribute("Lesequipments", equipmentList);

			List<IndexDao.YearClients> yearClientsList = indexDao.getClientsByYear();
			// Ajouter les données comme attribut à la requête
			Gson gson = new Gson();
			String yearClientsJson = gson.toJson(yearClientsList);
			request.setAttribute("yearClientsList", yearClientsList);

			RequestDispatcher list = request.getRequestDispatcher("index.jsp");
			list.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
