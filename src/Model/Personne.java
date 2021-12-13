package Model;

import java.util.Date;

public abstract class Personne {
	private String CIN;
	private String Prenom;
	private String Nom;
	private String Tel;
	private String Email;
	private char Sexe;
	private String date_naissance ;

	public String getCIN() {
		return CIN;
	}

	

	public Personne(String cIN, String prenom, String nom, String tel, String email, char sexe, String date_naissance2,
			String photo) {
		super();
		CIN = cIN;
		Prenom = prenom;
		Nom = nom;
		Tel = tel;
		Email = email;
		Sexe = sexe;
		this.date_naissance = date_naissance2;
		Photo = photo;
	}



	public Personne() {
		
	}



	@Override
	public String toString() {
		return "Personne [CIN=" + CIN + ", Prenom=" + Prenom + ", Nom=" + Nom + ", Tel=" + Tel + ", Email=" + Email
				+ ", Sexe=" + Sexe + ", date_naissance=" + date_naissance + ", Photo=" + Photo + "]";
	}



	public void setCIN(String cIN) {
		CIN = cIN;
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public char getSexe() {
		return Sexe;
	}

	public void setSexe(char sexe) {
		Sexe = sexe;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	private String Photo;

}
