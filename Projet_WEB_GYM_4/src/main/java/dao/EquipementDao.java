package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.bdConnect;
import model.Equipement;

public class EquipementDao {
	static Connection connection;

	public EquipementDao() throws ClassNotFoundException {
		connection = bdConnect.getConnection();
	}

	public List<Equipement> getEquipementsByName(String name) throws ClassNotFoundException, SQLException {
		List<Equipement> equipements = new ArrayList<>();
		String sql = "SELECT * FROM equipement WHERE nom LIKE ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Equipement eq = new Equipement();
				eq.setId(rs.getInt("id"));
				eq.setNom(rs.getString("nom"));
				eq.setQuantite(rs.getInt("quantite"));
				equipements.add(eq);
			}
		}
		return equipements;
	}

	public void addEquipement(Equipement eq) throws SQLException {
		String sql = "INSERT INTO equipement (nom, quantite) VALUES (?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, eq.getNom());
		stmt.setInt(2, eq.getQuantite());
		stmt.executeUpdate();
	}

	public static List<Equipement> getAllEquipements() throws ClassNotFoundException {
		List<Equipement> equipements = new ArrayList<Equipement>();
		String sql = "SELECT * FROM equipement";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Equipement eq = new Equipement();
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				int quantite = rs.getInt("quantite");
				eq.setId(id);
				eq.setQuantite(quantite);
				eq.setNom(nom);
				equipements.add(eq);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return equipements;
	}

	public void deleteEquipement(int id) throws SQLException {
		String sql = "delete from equipement where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}

	public Equipement getEquipementById(int id) throws SQLException {
		Equipement eq = new Equipement();
		String sql = "SELECT * FROM equipement where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			String nom = rs.getString("nom");
			int quantite = rs.getInt("quantite");
			eq.setId(id);
			eq.setQuantite(quantite);
			eq.setNom(nom);
		}
		return eq;
	}

	public static void updateEquipement(Equipement eq) throws ClassNotFoundException {
		String sql = "UPDATE equipement SET nom = ?, quantite = ? WHERE id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, eq.getNom());
			stmt.setInt(2, eq.getQuantite());
			stmt.setInt(3, eq.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
