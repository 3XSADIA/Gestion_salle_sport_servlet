package model;

public class Equipement {
	private int id;
	private String nom;
	private int quantite;

	public Equipement(int id1, String nom1, int qte1) {
		this.id = id1;
		this.nom = nom1;
		this.quantite = qte1;
	}

	public Equipement() {

	}

	public Equipement(String name, int quantity) {
		this.nom = name;
		this.quantite = quantity;
	}

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

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", nom=" + nom + ", quantite=" + quantite + "]";
	}
}
