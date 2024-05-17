package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bd.bdConnect;
import model.Client;
import model.Paiement;

public class PaiementDao {
	static Connection connection;

	public PaiementDao() throws ClassNotFoundException {
		connection = bdConnect.getConnection();
	}

	public void addpaiement(Paiement eq) throws SQLException {
		String sql = "insert into paiement (date_paiement,moispaye,montantpaye,id_client) values(?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setDate(1, new java.sql.Date(eq.getDate_paiement().getTime()));
		stmt.setString(2, eq.getMoispaye());
		stmt.setString(3, eq.getMontantpaye());
		stmt.setInt(4, eq.getId_client());
		stmt.executeUpdate();
	}

	public static List<Paiement> getAllpaiements() throws ClassNotFoundException {
		List<Paiement> paiements = new ArrayList<>();
		String sql = "SELECT p.id, p.id_client,p.date_paiement, p.moispaye, p.montantpaye, c.nom as clientName"
				+ " FROM paiement p " + "JOIN client c ON p.id_client = c.id ;";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Paiement eq = new Paiement();
				Date dp = rs.getDate("date_paiement");
				int id_client = rs.getInt("id_client");
				String mois = rs.getString("moispaye");
				String mont = rs.getString("montantpaye");
				int id = rs.getInt("id");

				eq.setId(id);
				eq.setMoispaye(mois);
				eq.setMontantpaye(mont);
				eq.setId_client(id_client);
				eq.setDate_paiement(dp);
				eq.setClientName(rs.getString("clientName"));
				paiements.add(eq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paiements;
	}

	public void deletepaiement(int id) throws SQLException {
		String sql = "delete from paiement where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}

	public Paiement getpaiementById(int id) throws SQLException {
		Paiement eq = new Paiement();
		String sql = "SELECT * FROM paiement where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Date dp = rs.getDate("date_paiement");
			int id_client = rs.getInt("id_client");
			String mois = rs.getString("moispaye");
			String mont = rs.getString("montantpaye");

			eq.setId(id);
			eq.setMoispaye(mois);
			eq.setMontantpaye(mont);
			eq.setId_client(id_client);
			eq.setDate_paiement(dp);

		}
		return eq;
	}

	public static void updatepaiement(Paiement eq) throws ClassNotFoundException {
		String sql = "update paiement set date_paiement=?,moispaye=?,montantpaye=?,id_client=? where id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setDate(1, new java.sql.Date(eq.getDate_paiement().getTime()));
			pst.setString(2, eq.getMoispaye());
			pst.setString(3, eq.getMontantpaye());
			pst.setInt(4, eq.getId_client());
			pst.setInt(5, eq.getId());
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Client> getAllClients() throws ClassNotFoundException {
		List<Client> clients = new ArrayList<Client>();
		String sql = "SELECT id,nom FROM client";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Client cl = new Client();
				cl.setId(rs.getInt("id"));
				cl.setNom(rs.getString("nom"));
				clients.add(cl);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}

}
