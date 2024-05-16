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

public class IndexDao {
	static Connection connection;

	public IndexDao() throws ClassNotFoundException {
		connection = bdConnect.getConnection();
	}

	public List<Equipement> getEquipmentData() throws SQLException {
		List<Equipement> equipmentList = new ArrayList<>();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT nom, quantite FROM equipement");
		while (resultSet.next()) {
			String name = resultSet.getString("nom");
			int quantity = resultSet.getInt("quantite");
			Equipement equipment = new Equipement(name, quantity);
			equipmentList.add(equipment);
		}
		return equipmentList;
	}

	public List<YearGains> getGainsByYear() throws SQLException {
		List<YearGains> yearGainsList = new ArrayList<>();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"SELECT YEAR(date_paiement) as year, SUM(montantpaye) as gains FROM paiement GROUP BY YEAR(date_paiement)");
		while (resultSet.next()) {
			int year = resultSet.getInt("year");
			int gains = resultSet.getInt("gains");
			System.out.println(year + " " + gains);
			YearGains yearGains = new YearGains(year, gains);
			yearGainsList.add(yearGains);
		}
		return yearGainsList;
	}

	public List<YearClients> getClientsByYear() throws SQLException {
		List<YearClients> yearClientsList = new ArrayList<>();

		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"SELECT YEAR(dateInscription) as year, COUNT(*) as total_clients FROM client GROUP BY YEAR(dateInscription)");
		while (resultSet.next()) {
			int year = resultSet.getInt("year");
			int totalClients = resultSet.getInt("total_clients");
			System.out.println(year + " " + totalClients);

			YearClients yearClients = new YearClients(year, totalClients);
			yearClientsList.add(yearClients);
		}
		return yearClientsList;
	}

	public static int getEquipement() throws SQLException {
		PreparedStatement pst = connection.prepareStatement("SELECT count(*) FROM equipement");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun équipement trouvé");
		}
	}

	public int getCoach() throws SQLException {
		PreparedStatement pst = connection.prepareStatement("SELECT count(*) FROM coach");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun coach trouvé");
		}
	}

	public static int getClient() throws SQLException {
		PreparedStatement pst = connection.prepareStatement("SELECT count(*) FROM client");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun client");
		}
	}

	public static int getSeance() throws SQLException {
		PreparedStatement pst = connection.prepareStatement("SELECT count(*) FROM seance");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun seance");
		}
	}

	public static int getPaiement() throws SQLException {
		PreparedStatement pst = connection.prepareStatement("SELECT count(*) FROM paiement");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun paiement");
		}
	}

	public static int getGains() throws SQLException {
		PreparedStatement pst = connection.prepareStatement("SELECT sum(montantpaye) FROM paiement");
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		} else {
			throw new SQLException("Aucun paiement");
		}
	}

	public class YearGains {
		private int year;
		private int gains;

		public YearGains(int year, int gains) {
			this.year = year;
			this.gains = gains;
		}

		public int getYear() {
			return year;
		}

		public int getGains() {
			return gains;
		}

		@Override
		public String toString() {
			return this.year + "" + this.gains;
		}
	}

	public class YearClients {
		private int year;
		private int totalClients;

		public YearClients(int year, int totalClients) {
			this.year = year;
			this.totalClients = totalClients;
		}

		public int getYear() {
			return year;
		}

		public int getTotalClients() {
			return totalClients;
		}

		@Override
		public String toString() {
			return  this.year + "" + this.totalClients;
		}
	}
}
