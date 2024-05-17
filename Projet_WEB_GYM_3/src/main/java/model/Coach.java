package model;

public class Coach {
	private int id;
	private String nom;
	private String prenom;
	private String cours;
	private String num_telephone;
	private String salaire;

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

	@Override
	public String toString() {
		return "Coach [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", Cours=" + cours + ", num_telephone="
				+ num_telephone + ", Salaire=" + salaire + "]";
	}

	public void setCours(String curs) {
		cours = curs;
	}

	public String getNum_telephone() {
		return num_telephone;
	}

	public void setNum_telephone(String num_telephone) {
		this.num_telephone = num_telephone;
	}

	public String getSalaire() {
		return salaire;
	}

	public void setSalaire(String sal) {
		salaire = sal;
	}

}
