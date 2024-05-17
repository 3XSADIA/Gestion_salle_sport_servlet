package web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

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

	private void generatePDF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"paiements.pdf\"");

		try (PdfWriter writer = new PdfWriter(response.getOutputStream());
				PdfDocument pdfDoc = new PdfDocument(writer);
				Document document = new Document(pdfDoc)) {

			// Create a table with 5 columns (since you have 5 headers)
			Table table = new Table(5);

			// Add table headers
			table.addCell(new Cell().add(new Paragraph("Id")));
			table.addCell(new Cell().add(new Paragraph("Id_Client")));
			table.addCell(new Cell().add(new Paragraph("Date de paiement")));
			table.addCell(new Cell().add(new Paragraph("Mois payé")));
			table.addCell(new Cell().add(new Paragraph("Montant payé")));

			// Add table rows
			for (Paiement paiement : dao.getAllpaiements()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String datePaiement = sdf.format(paiement.getDate_paiement());

				// Add data cells in the same order as headers
				table.addCell(new Cell().add(new Paragraph(String.valueOf(paiement.getId_client()))));
				table.addCell(new Cell().add(new Paragraph(datePaiement)));
				table.addCell(new Cell().add(new Paragraph(paiement.getMoispaye())));
				table.addCell(new Cell().add(new Paragraph(paiement.getMontantpaye())));
			}

			// Add table to document
			document.add(table);
		}
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
		case "generate":
			try {
				generatePDF(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return; // Terminer la méthode doGet() après avoir généré le PDF

		case "add":
			forward = "addPaiement.jsp";
			RequestDispatcher add = request.getRequestDispatcher("addPaiement.jsp");
			try {
				request.setAttribute("clients", dao.getAllClients());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			add.forward(request, response);
			break;
		case "edit":
			forward = "addPaiement.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addPaiement.jsp");
			id = Integer.parseInt(request.getParameter("id"));
			try {
				Paiement eq = dao.getpaiementById(id);
				request.setAttribute("paiement", eq);
				request.setAttribute("clients", dao.getAllClients());
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
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
		Paiement paiement = new Paiement();

		// Obtenez les paramètres du formulaire
		String dpString = request.getParameter("date_paiement");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date datePaiement = null;
		try {
			datePaiement = sdf.parse(dpString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int idClient = Integer.parseInt(request.getParameter("id_client"));
		String moisPaye = request.getParameter("moispaye");
		String montantPaye = request.getParameter("montantpaye");

		// Configuration des détails du paiement
		paiement.setDate_paiement(datePaiement);
		paiement.setId_client(idClient);
		paiement.setMoispaye(moisPaye);
		paiement.setMontantpaye(montantPaye);

		// Vérifier s'il s'agit d'une modification ou d'un ajout
		String id = request.getParameter("id").trim(); // Utiliser trim() pour supprimer les espaces
		if (!id.isEmpty()) {
			// Si un identifiant est fourni, il s'agit d'une modification
			try {
				paiement.setId(Integer.parseInt(id));
				dao.updatepaiement(paiement); // Mettre à jour le paiement existant
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			// Sinon, il s'agit d'un ajout
			try {
				dao.addpaiement(paiement); // Ajouter un nouveau paiement
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Mettre à jour la liste des paiements dans la requête
		try {
			request.setAttribute("Paiements", dao.getAllpaiements());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Transférer la requête à la page viewPaiements.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewPaiements.jsp");
		dispatcher.forward(request, response);
	}

}
