package web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

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

	private void generatePDF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException {
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"clients.pdf\"");

		try (PdfWriter writer = new PdfWriter(response.getOutputStream());
				PdfDocument pdfDoc = new PdfDocument(writer);
				Document document = new Document(pdfDoc)) {

			// Create a table with 6 columns
			Table table = new Table(6);

			// Add table headers
			table.addCell(new Cell().add(new Paragraph("Id")));
			table.addCell(new Cell().add(new Paragraph("Nom")));
			table.addCell(new Cell().add(new Paragraph("Prénom")));
			table.addCell(new Cell().add(new Paragraph("Date d'inscription")));
			table.addCell(new Cell().add(new Paragraph("Numéro de téléphone")));
			table.addCell(new Cell().add(new Paragraph("Cours")));

			// Add table rows
			for (Client client : dao.getAllClients()) {
				table.addCell(new Cell().add(new Paragraph(String.valueOf(client.getId()))));
				table.addCell(new Cell().add(new Paragraph(client.getNom())));
				table.addCell(new Cell().add(new Paragraph(client.getPrenom())));
				table.addCell(new Cell().add(new Paragraph(client.getDateInscription().toString())));
				table.addCell(new Cell().add(new Paragraph(client.getNumTelephone())));
				table.addCell(new Cell().add(new Paragraph(client.getCours())));
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
				request.setAttribute("clients", dao.getCleintsByName(searchQuery));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				request.setAttribute("clients", dao.getAllClients());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
		case "generate":
			try {
				generatePDF(request, response);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return; // Terminer la méthode doGet() après avoir généré le PDF

		case "edit":
			forward = "addClient.jsp";
			RequestDispatcher edit = request.getRequestDispatcher("addClient.jsp");

			int id_ = Integer.parseInt(request.getParameter("id"));

			try {
				request.setAttribute("client", dao.getClientById(id_));

				request.setAttribute("courses", dao.getAllCourses());

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			edit.forward(request, response);

			break;
		case "add":
			forward = "addClient.jsp";
			RequestDispatcher add = request.getRequestDispatcher("addClient.jsp");

			try {
				request.setAttribute("courses", dao.getAllCourses());

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			add.forward(request, response);
			break;
		default:
			RequestDispatcher view = request.getRequestDispatcher("viewClients.jsp");
			try {
				request.setAttribute("clients", dao.getAllClients());
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

		// Obtenez les paramètres du formulaire
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String tele = request.getParameter("NumTelephone");
		String dpString = request.getParameter("dateInscription");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dpDte = null;
		try {
			dpDte = new Date(sdf.parse(dpString).getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String cours = request.getParameter("Cours");

		// Configuration des détails du client
		cl.setNom(nom);
		cl.setPrenom(prenom);
		cl.setCours(cours);
		cl.setDateInscription(dpDte);
		cl.setNumTelephone(tele);

		// Vérifier s'il s'agit d'une modification ou d'un ajout
		String id = request.getParameter("id").trim(); // Utiliser trim() pour supprimer les espaces
		if (!id.isEmpty()) {
			// Si un identifiant est fourni, il s'agit d'une modification
			try {
				cl.setId(Integer.parseInt(id));
				dao.updateClient(cl); // Mettre à jour le client existant
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			// Sinon, il s'agit d'un ajout
			try {
				dao.addClient(cl); // Ajouter un nouveau client
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Mettre à jour la liste des clients dans la requête
		try {
			request.setAttribute("clients", dao.getAllClients());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Transférer la requête à la page viewClients.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewClients.jsp");
		dispatcher.forward(request, response);
	}

}