package Model;

public class Prof extends Personne {
	private int Salaire;
	private String MatiereEnseign�;
	private String pseudo;
	private String password;

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSalaire() {
		return Salaire;
	}

	public void setSalaire(int salaire) {
		Salaire = salaire;
	}

	public Prof() {
		super();
	}

	public String getMatiereEnseign�() {
		return MatiereEnseign�;
	}

	public void setMatiereEnseign�(String matiereEnseign�) {
		MatiereEnseign� = matiereEnseign�;
	}

	public Prof(String cIN, String prenom, String nom, String tel, String email, char sexe, String date_naissance,
			String photo, int salaire, String matiereEnseign�) {
		super(cIN, prenom, nom, tel, email, sexe, date_naissance, photo);
		Salaire = salaire;
		MatiereEnseign� = matiereEnseign�;
	}

	

}
