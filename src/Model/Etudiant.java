package Model;

import java.util.Date;

public class Etudiant extends Personne {
	
	private String NumeroClasse;

	

	

	

	@Override
	public String toString() {
		return "Etudiant [NumeroClasse=" + NumeroClasse + ", toString()=" + super.toString() + "]";
	}

	public String getNumeroClasse() {
		return NumeroClasse;
	}

	public void setNumeroClasse(String numeroClasse) {
		NumeroClasse = numeroClasse;
	}

	public Etudiant(String cIN, String prenom, String nom, String tel, String email, char sexe, String date_naissance,
			String photo, String numeroClasse) {
		super(cIN, prenom, nom, tel, email, sexe, date_naissance, photo);
		NumeroClasse = numeroClasse;
	}

	

	
	
}
