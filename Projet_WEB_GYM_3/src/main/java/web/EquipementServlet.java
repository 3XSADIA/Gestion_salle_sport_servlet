package web;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

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

	private void generatePDF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"equipements.pdf\"");

		try (PdfWriter writer = new PdfWriter(response.getOutputStream());
				PdfDocument pdfDoc = new PdfDocument(writer);
				Document document = new Document(pdfDoc)) {

			// Create a table with 3 columns
			Table table = new Table(3);

			// Add table headers
			table.addCell(new Cell().add(new Paragraph("Id")));
			table.addCell(new Cell().add(new Paragraph("Nom")));
			table.addCell(new Cell().add(new Paragraph("Quantité")));

			// Add table rows
			for (Equipement equipement : dao.getAllEquipements()) {
				table.addCell(new Cell().add(new Paragraph(String.valueOf(equipement.getId()))));
				table.addCell(new Cell().add(new Paragraph(equipement.getNom())));
				table.addCell(new Cell().add(new Paragraph(String.valueOf(equipement.getQuantite()))));
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
		case "generate":
			try {
				generatePDF(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return; // Terminer la méthode doGet() après avoir généré le PDF

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
		Equipement equipment = new Equipement();

		// Obtenez les paramètres du formulaire
		String name = request.getParameter("name");
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		// Configuration des détails de l'équipement
		equipment.setNom(name);
		equipment.setQuantite(quantity);

		// Vérifier s'il s'agit d'une modification ou d'un ajout
		String id = request.getParameter("id").trim(); // Utiliser trim() pour supprimer les espaces
		if (!id.isEmpty()) {
			// Si un identifiant est fourni, il s'agit d'une modification
			try {
				equipment.setId(Integer.parseInt(id));
				dao.updateEquipement(equipment); // Mettre à jour l'équipement existant
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			// Sinon, il s'agit d'un ajout
			try {
				dao.addEquipement(equipment); // Ajouter un nouvel équipement
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Mettre à jour la liste des équipements dans la requête
		try {
			request.setAttribute("equipments", dao.getAllEquipements());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Transférer la requête à la page viewEquipments.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewEquipments.jsp");
		dispatcher.forward(request, response);
	}

}
