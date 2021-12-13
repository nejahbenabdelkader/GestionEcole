package Model;



public class Class {
	private String Id;
	private String Filliere;
	private int NombreEtudiants;
	private Etudiant[] Etudiants;
	private String EmploiDuTemps;
	private String Niveau ;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getFilliere() {
		return Filliere;
	}

	public void setFilliere(String filliere) {
		Filliere = filliere;
	}

	public int getNombreEtudiants() {
		return NombreEtudiants;
	}

	public void setNombreEtudiants(int nombreEtudiants) {
		NombreEtudiants = nombreEtudiants;
	}

	public Etudiant[] getEtudiants() {
		return Etudiants;
	}

	public void setEtudiants(Etudiant[] etudiants) {
		Etudiants = etudiants;
	}

	public String getEmploiDuTemps() {
		return EmploiDuTemps;
	}

	public void setEmploiDuTemps(String emploiDuTemps) {
		EmploiDuTemps = emploiDuTemps;
	}

	public Class(String id, String filliere,String niveau, int nombreEtudiants,  String emploiDuTemps) {
		Id = id;
		Filliere = filliere;
		NombreEtudiants = nombreEtudiants;
		setNiveau(niveau) ;
		EmploiDuTemps = emploiDuTemps;
	}

	public Class() {
		// TODO Auto-generated constructor stub
	}

	public String getNiveau() {
		return Niveau;
	}

	public void setNiveau(String niveau) {
		Niveau = niveau;
	}
	
}
