package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.bdConnect;
import model.Coach;
import model.Cours;
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
		List<Seance> seances = new ArrayList<>();
		String sql = "SELECT s.id, s.id_coach, s.horaire, s.id_cours, c.nom as coachName, co.label as courseLabel "
				+ "FROM seance s " + "JOIN coach c ON s.id_coach = c.id " + "JOIN cours co ON s.id_cours = co.id";

		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Seance seance = new Seance();
				seance.setId(rs.getInt("id"));
				seance.setId_coach(rs.getInt("id_coach"));
				seance.setHoraire(rs.getString("horaire"));
				seance.setId_cours(rs.getInt("id_cours"));
				seance.setCoachName(rs.getString("coachName")); // Set coach name
				seance.setCourseLabel(rs.getString("courseLabel")); // Set course label
				seances.add(seance);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seances;
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

	public static List<Cours> getAllCourses() throws ClassNotFoundException {
		List<Cours> courses = new ArrayList<Cours>();
		String sql = "SELECT id,label FROM cours";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Cours cour = new Cours();
				cour.setId(rs.getInt("id"));
				cour.setLabel(rs.getString("label"));
				courses.add(cour);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}

	public static List<Coach> getAllCoachs() throws ClassNotFoundException {
		List<Coach> coachs = new ArrayList<Coach>();
		String sql = "SELECT id,nom FROM coach";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Coach coach = new Coach();
				coach.setId(rs.getInt("id"));
				coach.setNom(rs.getString("nom"));
				coachs.add(coach);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coachs;
	}
}
