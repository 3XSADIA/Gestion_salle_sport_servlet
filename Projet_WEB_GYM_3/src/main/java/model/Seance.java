package model;

public class Seance {
	private int id;
	private int id_coach;
	private int id_cours;
	private String horaire;

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Seance [id=" + id + ", id_coach=" + id_coach + ", id_cours=" + id_cours + ", horaire=" + horaire + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_coach() {
		return id_coach;
	}

	public void setId_coach(int id_coach) {
		this.id_coach = id_coach;
	}

	public int getId_cours() {
		return id_cours;
	}

	public void setId_cours(int id_cours) {
		this.id_cours = id_cours;
	}

	public String getHoraire() {
		return horaire;
	}

	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}

}
