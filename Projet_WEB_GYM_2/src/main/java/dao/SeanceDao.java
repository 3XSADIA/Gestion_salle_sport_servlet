package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.bdConnect;
import model.Seance;

public class SeanceDao {
	static Connection connection;

	public SeanceDao() throws ClassNotFoundException {
		connection = bdConnect.getConnection();
	}

	public void addSeance(Seance eq) throws SQLException {
		String sql = "insert into seance (id_coach,horaire,id_cours) values(?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, eq.getId_coach());
		stmt.setString(2, eq.getHoraire());
		stmt.setInt(3, eq.getId_cours());

		stmt.executeUpdate();
	}

	public static List<Seance> getAllSeances() throws ClassNotFoundException {
		List<Seance> Seances = new ArrayList<Seance>();
		String sql = "SELECT * FROM seance";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Seance eq = new Seance();
				int id = rs.getInt("id");
				int id_coach = rs.getInt("id_coach");
				int id_cours = rs.getInt("id_cours");
				String horaire = rs.getString("horaire");
				eq.setId(id);
				eq.setId_coach(id_coach);
				eq.setId_cours(id_cours);
				eq.setHoraire(horaire);
				Seances.add(eq);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Seances;
	}

	public void deleteSeance(int id) throws SQLException {
		String sql = "delete from seance where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}

	public Seance getSeanceById(int id) throws SQLException {
		Seance eq = new Seance();
		String sql = "SELECT * FROM seance where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id_coach = rs.getInt("id_coach");
			int id_cours = rs.getInt("id_cours");
			String horaire = rs.getString("horaire");

			eq.setId(id);
			eq.setId_coach(id_coach);
			eq.setId_cours(id_cours);
			eq.setHoraire(horaire);
		}
		return eq;
	}

	public static void updateSeance(Seance eq) throws ClassNotFoundException {
		String sql = "update seance set id_coach=?,horaire=?,id_cours=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, eq.getId_coach());
			stmt.setString(2, eq.getHoraire());
			stmt.setInt(3, eq.getId_cours());
			stmt.setInt(4, eq.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
