package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bd.bdConnect;
import model.Client;

public class ClientDao {
	static Connection connection;

	public ClientDao() throws ClassNotFoundException {
		connection = bdConnect.getConnection();
	}

	public void addClient(Client eq) throws SQLException {
		PreparedStatement pst = connection.prepareStatement(
				"insert into client (nom,prenom,NumTelephone,dateInscription,Cours) values(?,?,?,?,?)");
		pst.setString(1, eq.getNom());
		pst.setString(2, eq.getPrenom());
		pst.setString(3, eq.getNumTelephone());
		pst.setTimestamp(4, new Timestamp(eq.getDateInscription().getTime()));
		pst.setString(5, eq.getCours());
		pst.executeUpdate();
	}

	public static List<String> getAllCourses() throws ClassNotFoundException {
		List<String> courses = new ArrayList<String>();
		String sql = "SELECT label FROM cours";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String course = rs.getString("label");
				System.out.println(course);
				courses.add(course);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	public static List<Client> getAllClients() throws ClassNotFoundException {
		List<Client> Clients = new ArrayList<Client>();
		String sql = "SELECT * FROM client";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Client cl = new Client();
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String tele = rs.getString("NumTelephone");
				Date di = rs.getDate("dateInscription");
				String cours = rs.getString("Cours");
				cl.setId(id);
				cl.setNom(nom);
				cl.setPrenom(prenom);
				cl.setCours(cours);
				cl.setDateInscription(di);
				cl.setNumTelephone(tele);
				Clients.add(cl);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Clients;
	}

	public void deleteClient(int id) throws SQLException {
		String sql = "delete from client where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}

	public Client getClientById(int id) throws SQLException {
		Client eq = new Client();
		String sql = "SELECT * FROM client where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Client cl = new Client();
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String tele = rs.getString("NumTelephone");
			Date di = rs.getDate("dateInscription");
			String cours = rs.getString("Cours");
			cl.setId(id);
			cl.setNom(nom);
			cl.setPrenom(prenom);
			cl.setCours(cours);
			cl.setDateInscription(di);
		}
		return eq;
	}

	public static void updateClient(Client eq) throws ClassNotFoundException {
		String sql = "update client set nom=?,prenom=?,NumTelephone=?,dateInscription=?,Cours=?  where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, eq.getNom());
			stmt.setString(2, eq.getPrenom());
			stmt.setString(3, eq.getNumTelephone());
			stmt.setTimestamp(4, new Timestamp(eq.getDateInscription().getTime()));
			stmt.setString(5, eq.getCours());
			stmt.setInt(6, eq.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
