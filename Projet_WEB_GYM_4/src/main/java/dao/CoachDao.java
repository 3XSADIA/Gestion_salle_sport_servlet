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

public class CoachDao {
	static Connection connection;

	public CoachDao() throws ClassNotFoundException {
		connection = bdConnect.getConnection();
	}

	public List<Coach> getCoachsByName(String name) throws ClassNotFoundException, SQLException {
		List<Coach> Coachs = new ArrayList<Coach>();
		String sql = "SELECT * FROM coach WHERE nom LIKE ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Coach cl = new Coach();
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String tele = rs.getString("num_telephone");
				String sal = rs.getString("Salaire");
				String cours = rs.getString("Cours");
				cl.setId(id);
				cl.setNom(nom);
				cl.setPrenom(prenom);
				cl.setCours(cours);
				cl.setSalaire(sal);
				cl.setNum_telephone(tele);
				Coachs.add(cl);
			}
		}
		return Coachs;
	}

	public void addCoach(Coach eq) throws SQLException {
		PreparedStatement pst = connection
				.prepareStatement("insert into coach (nom,prenom,num_telephone,Cours,Salaire) values(?,?,?,?,?)");
		pst.setString(1, eq.getNom());
		pst.setString(2, eq.getPrenom());
		pst.setString(3, eq.getNum_telephone());
		pst.setString(4, eq.getCours());
		pst.setString(5, eq.getSalaire());

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

	public static List<Coach> getAllCoachs() throws ClassNotFoundException {
		List<Coach> Coachs = new ArrayList<Coach>();
		String sql = "SELECT * FROM coach";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Coach cl = new Coach();
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String tele = rs.getString("num_telephone");
				String sal = rs.getString("Salaire");
				String cours = rs.getString("Cours");
				cl.setId(id);
				cl.setNom(nom);
				cl.setPrenom(prenom);
				cl.setCours(cours);
				cl.setSalaire(sal);
				cl.setNum_telephone(tele);
				Coachs.add(cl);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Coachs;
	}

	public void deleteCoach(int id) throws SQLException {
		String sql = "delete from coach where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}

	public Coach getCoachById(int id) throws SQLException {
		Coach cl = new Coach();
		String sql = "SELECT * FROM coach where id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			cl = new Coach();
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String tele = rs.getString("num_telephone");
			String sal = rs.getString("Salaire");
			String cours = rs.getString("Cours");
			cl.setId(id);
			cl.setNom(nom);
			cl.setPrenom(prenom);
			cl.setCours(cours);
			cl.setSalaire(sal);
			cl.setNum_telephone(tele);
		}
		return cl;
	}

	public static void updateCoach(Coach eq) throws ClassNotFoundException {
		String sql = "update coach set nom=?,prenom=?,num_telephone=?,Cours=?,salaire=? where id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, eq.getNom());
			stmt.setString(2, eq.getPrenom());
			stmt.setString(3, eq.getNum_telephone());
			stmt.setString(4, eq.getCours());
			stmt.setString(5, eq.getSalaire());
			stmt.setInt(6, eq.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
