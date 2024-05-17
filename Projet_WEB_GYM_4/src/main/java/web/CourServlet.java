package web;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

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

	private void generatePDF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"cours.pdf\"");

		try (PdfWriter writer = new PdfWriter(response.getOutputStream());
				PdfDocument pdfDoc = new PdfDocument(writer);
				Document document = new Document(pdfDoc)) {

			// Create a table with 6 columns
			Table table = new Table(3);

			// Add table headers
			table.addCell(new Cell().add(new Paragraph("Id")));
			table.addCell(new Cell().add(new Paragraph("Label")));
			table.addCell(new Cell().add(new Paragraph("Equipements")));

			// Add table rows
			for (Cours cours : dao.getAllCours()) {
				table.addCell(new Cell().add(new Paragraph(String.valueOf(cours.getId()))));
				table.addCell(new Cell().add(new Paragraph(cours.getLabel())));
				table.addCell(new Cell().add(new Paragraph(cours.getEquipements())));
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
		case "generate":
			try {
				generatePDF(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return; // Terminer la méthode doGet() après avoir généré le PDF

		case "edit":
			forward = "addCours.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addCours.jsp");

			int id_ = Integer.parseInt(request.getParameter("id"));

			try {
				request.setAttribute("cours", dao.getCoursById(id_));

				request.setAttribute("equipements", dao.getAllEquipements());

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			edit.forward(request, response);

			break;
		case "add":
			forward = "addCours.jsp";
			RequestDispatcher add = request.getRequestDispatcher("addCours.jsp");

			try {
				request.setAttribute("equipements", dao.getAllEquipements());

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			add.forward(request, response);
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
		Cours cours = new Cours();

		// Obtenez les paramètres du formulaire
		String label = request.getParameter("label");
		String equipements = request.getParameter("equipements");

		// Configuration des détails du cours
		cours.setLabel(label);
		cours.setEquipements(equipements);

		// Vérifier s'il s'agit d'une modification ou d'un ajout
		String id = request.getParameter("id").trim(); // Utiliser trim() pour supprimer les espaces
		if (!id.isEmpty()) {
			// Si un identifiant est fourni, il s'agit d'une modification
			try {
				cours.setId(Integer.parseInt(id));
				dao.updateCours(cours); // Mettre à jour le cours existant
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			// Sinon, il s'agit d'un ajout
			try {
				dao.addCours(cours); // Ajouter un nouveau cours
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Mettre à jour la liste des cours dans la requête
		try {
			request.setAttribute("cours", dao.getAllCours());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Transférer la requête à la page viewCours.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewCours.jsp");
		dispatcher.forward(request, response);
	}

}
