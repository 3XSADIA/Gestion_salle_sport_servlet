package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.bdConnect;
import model.Cours;

public class CoursDao {
	static Connection connection;

	public CoursDao() throws ClassNotFoundException {
		connection = bdConnect.getConnection();
	}

	public void addCours(Cours eq) throws SQLException {
		String sql = "insert into cours (label,equipements) values(?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, eq.getLabel());
		stmt.setString(2, eq.getEquipements());
		stmt.executeUpdate();
	}

	public static List<Cours> getAllCours() throws ClassNotFoundException {
		List<Cours> Cours = new ArrayList<Cours>();
		String sql = "SELECT * FROM cours";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Cours eq = new Cours();
				int id = rs.getInt("id");
				String nom = rs.getString("label");
				String equipements = rs.getString("equipements");
				eq.setId(id);
				eq.setEquipements(equipements);
				eq.setLabel(nom);
				Cours.add(eq);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Cours;
	}

	public void deleteCours(int id) throws SQLException {
		String sql = "delete from cours where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}

	public Cours getCoursById(int id) throws SQLException {
		Cours eq = new Cours();
		String sql = "SELECT * FROM cours where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			String nom = rs.getString("label");
			String equipements = rs.getString("equipements");
			eq.setId(id);
			eq.setEquipements(equipements);
			eq.setLabel(nom);
		}
		return eq;
	}

	public static void updateCours(Cours eq) throws ClassNotFoundException {
		String sql = "update cours set label=?,equipements=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, eq.getLabel());
			stmt.setString(2, eq.getEquipements());
			stmt.setInt(3, eq.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
