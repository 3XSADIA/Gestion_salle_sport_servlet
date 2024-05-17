package model;

import java.util.Date;

public class Client {
	private String nom;
	private String prenom;
	private String cours;
	private String numTelephone;
	private Date dateInscription;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCours() {
		return cours;
	}

	public void setCours(String cos) {
		cours = cos;
	}

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", cours=" + cours + ", numTelephone=" + numTelephone
				+ ", dateInscription=" + dateInscription + ", id=" + id + "]";
	}

	public String getNumTelephone() {
		return numTelephone;
	}

	public void setNumTelephone(String nuTelephone) {
		numTelephone = nuTelephone;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

}
