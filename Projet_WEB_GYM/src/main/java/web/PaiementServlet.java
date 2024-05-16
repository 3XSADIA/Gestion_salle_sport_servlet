package web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.PaiementDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Paiement;

/**
 * Servlet implementation class PaiementServlet
 */
@WebServlet("/PaiementServlet")
public class PaiementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PaiementDao dao;

	public PaiementServlet() throws ClassNotFoundException {
		dao = new PaiementDao();
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
				dao.deletepaiement(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forward = "viewPaiements.jsp";
			RequestDispatcher del = request.getRequestDispatcher("viewPaiements.jsp");
			try {
				request.setAttribute("Paiements", dao.getAllpaiements());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			del.forward(request, response);

			break;

		case "edit":
			forward = "addPaiement.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addPaiement.jsp");

			id = Integer.parseInt(request.getParameter("id"));
			try {
				Paiement eq = dao.getpaiementById(id);
				request.setAttribute("Paiement", eq);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			edit.forward(request, response);

			break;

		default:
			RequestDispatcher view = request.getRequestDispatcher("viewPaiements.jsp");
			try {
				request.setAttribute("Paiements", dao.getAllpaiements());
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
		Paiement eq = new Paiement();
		String nom = request.getParameter("nom");
		int quantite = Integer.parseInt(request.getParameter("quantite"));
		String dpString = request.getParameter("date_paiement");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dpDte = null;
		try {
			dpDte = sdf.parse(dpString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int id_client = Integer.parseInt(request.getParameter("id_client"));
		String mois = request.getParameter("moispaye");
		String mont = request.getParameter("montantpaye");
		eq.setMoispaye(mois);
		eq.setMontantpaye(mont);
		eq.setId_client(id_client);
		eq.setDate_paiement(dpDte);

		String id = (request.getParameter("id"));
		if (id == null || id.isEmpty()) {
			try {
				dao.addpaiement(eq);
				RequestDispatcher list_ = request.getRequestDispatcher("viewPaiements.jsp");
				request.setAttribute("Paiements", dao.getAllpaiements());
				list_.forward(request, response);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			eq.setId(Integer.parseInt(id));
			try {
				PaiementDao.updatepaiement(eq);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher list = request.getRequestDispatcher("viewPaiements.jsp");
			try {
				request.setAttribute("Paiements", dao.getAllpaiements());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.forward(request, response);
		}

	}

}
