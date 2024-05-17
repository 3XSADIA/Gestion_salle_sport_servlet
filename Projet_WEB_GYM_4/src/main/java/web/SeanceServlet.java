package web;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import dao.SeanceDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Seance;

@WebServlet("/SeanceServlet")
public class SeanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SeanceDao dao;

	public SeanceServlet() throws ClassNotFoundException {
		dao = new SeanceDao();
	}

	private void generatePDF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"seances.pdf\"");

		try (PdfWriter writer = new PdfWriter(response.getOutputStream());
				PdfDocument pdfDoc = new PdfDocument(writer);
				Document document = new Document(pdfDoc)) {

			// Create a table with 4 columns
			Table table = new Table(4);

			// Add table headers
			table.addCell(new Cell().add(new Paragraph("ID")));
			table.addCell(new Cell().add(new Paragraph("ID Coach")));
			table.addCell(new Cell().add(new Paragraph("ID Cours")));
			table.addCell(new Cell().add(new Paragraph("Horaire")));

			// Add table rows
			for (Seance seance : dao.getAllSeances()) {
				table.addCell(new Cell().add(new Paragraph(String.valueOf(seance.getId()))));
				table.addCell(new Cell().add(new Paragraph(String.valueOf(seance.getId_coach()))));
				table.addCell(new Cell().add(new Paragraph(String.valueOf(seance.getId_cours()))));
				table.addCell(new Cell().add(new Paragraph(seance.getHoraire())));
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
				dao.deleteSeance(id);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			forward = "viewSeances.jsp";
			RequestDispatcher del = request.getRequestDispatcher("viewSeances.jsp");
			try {
				request.setAttribute("Seances", dao.getAllSeances());
			} catch (ClassNotFoundException e1) {
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
			return;
		case "add":
			forward = "addSeance.jsp";
			RequestDispatcher add = request.getRequestDispatcher("addSeance.jsp");
			try {
				request.setAttribute("courses", dao.getAllCourses());
				request.setAttribute("coachs", dao.getAllCoachs());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			add.forward(request, response);
			break;
		case "edit":
			forward = "addSeance.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addSeance.jsp");
			id = Integer.parseInt(request.getParameter("id"));
			try {
				Seance eq = dao.getSeanceById(id);
				request.setAttribute("Seance", eq);
				request.setAttribute("courses", dao.getAllCourses());
				request.setAttribute("coachs", dao.getAllCoachs());
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			edit.forward(request, response);
			break;
		default:
			RequestDispatcher view = request.getRequestDispatcher("viewSeances.jsp");
			try {
				request.setAttribute("Seances", dao.getAllSeances());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			view.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Seance seance = new Seance();

		// Obtenez les paramètres du formulaire
		int idCoach = Integer.parseInt(request.getParameter("id_coach"));
		int idCours = Integer.parseInt(request.getParameter("id_cours"));
		String horaire = request.getParameter("horaire");

		// Configuration des détails de la séance
		seance.setId_coach(idCoach);
		seance.setId_cours(idCours);
		seance.setHoraire(horaire);

		// Vérifier s'il s'agit d'une modification ou d'un ajout
		String id = request.getParameter("id").trim();
		if (!id.isEmpty()) {
			// Si un identifiant est fourni, il s'agit d'une modification
			try {
				seance.setId(Integer.parseInt(id));
				dao.updateSeance(seance); // Mettre à jour la séance existante
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			// Sinon, il s'agit d'un ajout
			try {
				dao.addSeance(seance); // Ajouter une nouvelle séance
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Mettre à jour la liste des séances dans la requête
		try {
			request.setAttribute("Seances", dao.getAllSeances());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Transférer la requête à la page viewSeances.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewSeances.jsp");
		dispatcher.forward(request, response);
	}
}
