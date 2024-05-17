package web;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import dao.CoachDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Coach;

/**
 * Servlet implementation class CoachServlet
 */
@WebServlet("/CoachServlet")
public class CoachServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CoachDao dao;

	public CoachServlet() throws ClassNotFoundException {
		dao = new CoachDao();
	}

	private void generatePDF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"coachs.pdf\"");

		try (PdfWriter writer = new PdfWriter(response.getOutputStream());
				PdfDocument pdfDoc = new PdfDocument(writer);
				Document document = new Document(pdfDoc)) {

			// Create a table with 6 columns
			Table table = new Table(6);

			// Add table headers
			table.addCell(new Cell().add(new Paragraph("Id")));
			table.addCell(new Cell().add(new Paragraph("Nom")));
			table.addCell(new Cell().add(new Paragraph("Prénom")));
			table.addCell(new Cell().add(new Paragraph("Salaire")));
			table.addCell(new Cell().add(new Paragraph("Numéro de téléphone")));
			table.addCell(new Cell().add(new Paragraph("Cours")));

			// Add table rows
			for (Coach c : dao.getAllCoachs()) {
				table.addCell(new Cell().add(new Paragraph(String.valueOf(c.getId()))));
				table.addCell(new Cell().add(new Paragraph(c.getNom())));
				table.addCell(new Cell().add(new Paragraph(c.getPrenom())));
				table.addCell(new Cell().add(new Paragraph(c.getSalaire())));
				table.addCell(new Cell().add(new Paragraph(c.getNum_telephone())));
				table.addCell(new Cell().add(new Paragraph(c.getCours())));
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
		String searchQuery = request.getParameter("search");
		if (searchQuery != null && !searchQuery.isEmpty()) {
			try {
				request.setAttribute("coachs", dao.getCoachsByName(searchQuery));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				request.setAttribute("coachs", dao.getAllCoachs());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		switch (action) {
		case "delete":
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				dao.deleteCoach(id);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			forward = "viewCoachs.jsp";
			RequestDispatcher del = request.getRequestDispatcher("viewCoachs.jsp");
			try {
				request.setAttribute("coach", dao.getCoachById(id));
				request.setAttribute("coachs", dao.getAllCoachs());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			forward = "addCoach.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addCoach.jsp");

			int id_ = Integer.parseInt(request.getParameter("id"));

			try {
				request.setAttribute("coach", dao.getCoachById(id_));

				request.setAttribute("courses", dao.getAllCourses());

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
			forward = "addCoach.jsp";
			RequestDispatcher add = request.getRequestDispatcher("addCoach.jsp");

			try {
				request.setAttribute("courses", dao.getAllCourses());

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			add.forward(request, response);

		default:
			RequestDispatcher view = request.getRequestDispatcher("viewCoachs.jsp");
			try {
				request.setAttribute("coachs", dao.getAllCoachs());
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
		Coach coach = new Coach();

		// Obtenez les paramètres du formulaire
		String name = request.getParameter("nom");
		String lastName = request.getParameter("prenom");
		String phoneNumber = request.getParameter("num_telephone");
		String salary = request.getParameter("Salaire");
		String course = request.getParameter("Cours");

		// Configuration des détails du coach
		coach.setNom(name);
		coach.setPrenom(lastName);
		coach.setNum_telephone(phoneNumber);
		coach.setSalaire(salary);
		coach.setCours(course);

		// Vérifier s'il s'agit d'une modification ou d'un ajout
		String id = request.getParameter("id").trim(); // Utiliser trim() pour supprimer les espaces
		if (!id.isEmpty()) {
			// Si un identifiant est fourni, il s'agit d'une modification
			try {
				coach.setId(Integer.parseInt(id));
				dao.updateCoach(coach); // Mettre à jour le coach existant
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			// Sinon, il s'agit d'un ajout
			try {
				dao.addCoach(coach); // Ajouter un nouveau coach
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Mettre à jour la liste des coachs dans la requête
		try {
			request.setAttribute("coachs", dao.getAllCoachs());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Transférer la requête à la page viewCoachs.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewCoachs.jsp");
		dispatcher.forward(request, response);
	}

}
