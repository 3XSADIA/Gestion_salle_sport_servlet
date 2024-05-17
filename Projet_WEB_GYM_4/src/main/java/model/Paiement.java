package model;

import java.util.Date;

public class Paiement {
	private int id;
	private Date date_paiement;
	private String moispaye;
	private String montantpaye;
	private int id_client;
	private String clientName; // Add this

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clienthName) {
		this.clientName = clienthName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_paiement() {
		return date_paiement;
	}

	public void setDate_paiement(Date date_paiement) {
		this.date_paiement = date_paiement;
	}

	public String getMoispaye() {
		return moispaye;
	}

	public void setMoispaye(String moispaye) {
		this.moispaye = moispaye;
	}

	public String getMontantpaye() {
		return montantpaye;
	}

	public void setMontantpaye(String montantpaye) {
		this.montantpaye = montantpaye;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

}
