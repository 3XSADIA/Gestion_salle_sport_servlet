package web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.IndexDao;
import dao.IndexDao.YearClients;
import dao.IndexDao.YearGains;
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
			// Affichage de la liste dans la console
			System.out.println("Liste des équipements :");
			for (Equipement equipement : equipmentList) {
				System.out.println("Nom : " + equipement.getNom() + ", Quantité : " + equipement.getQuantite());
			}
			request.setAttribute("Lesequipments", equipmentList);

			List<IndexDao.YearClients> yearClientsList = indexDao.getClientsByYear();
			System.out.println("Liste des Clients :");
			for (YearClients equipement : yearClientsList) {
				System.out.println(
						"getYear " + equipement.getYear() + ", getTotalClients : " + equipement.getTotalClients());
			}
			request.setAttribute("yearClientsList", yearClientsList);

			List<YearGains> yearGainsList = indexDao.getGainsByYear();
			System.out.println("Liste des Gains :");
			for (YearGains equipement : yearGainsList) {
				System.out.println("getYear : " + equipement.getYear() + ", getGains : " + equipement.getGains());
			}
			request.setAttribute("yearGainsList", yearGainsList);

			RequestDispatcher list = request.getRequestDispatcher("index.jsp");
			list.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
