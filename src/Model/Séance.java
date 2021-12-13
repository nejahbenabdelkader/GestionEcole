package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import application.BD;

public class Séance {
	private String Salle;
	private Prof ProfEnseigné;
	private String MatiereEtudié;
    private String Emploi_id;
    @Override
	public String toString() {
		return "Séance [Salle=" + Salle + ", ProfEnseigné=" + ProfEnseigné + ", MatiereEtudié=" + MatiereEtudié
				+ ", Emploi_id=" + Emploi_id + ", Seance_id=" + Seance_id + "]";
	}

	private String Seance_id;

	public Séance(String salle, String profEnseigné, String string, String emploi_id, String seance_id) throws SQLException {
		Salle = salle;
		MatiereEtudié = string;
		Emploi_id = emploi_id;
		Seance_id = seance_id;
		ProfEnseigné=new Prof();
		ResultSet result=BD.getResult("SELECT  `NOM`, `PRENOM`, `SEXE`, `EMAIL`, `TEL` FROM `enseignant` WHERE CIN="+profEnseigné);
		result.next();
		ProfEnseigné.setCIN(profEnseigné);
		ProfEnseigné.setNom(result.getString("NOM"));
		ProfEnseigné.setPrenom(result.getString("PRENOM"));
		
	}

	public String getSalle() {
		return Salle;
	}

	public void setSalle(String salle) {
		Salle = salle;
	}

	public Prof getProfEnseigné() {
		return ProfEnseigné;
	}

	public void setProfEnseigné(Prof profEnseigné) {
		ProfEnseigné = profEnseigné;
	}

	public String getMatiereEtudié() {
		return MatiereEtudié;
	}

	public void setMatiereEtudié(String matiereEtudié) {
		MatiereEtudié = matiereEtudié;
	}

	
	public String getEmploi_id() {
		return Emploi_id;
	}

	public void setEmploi_id(String emploi_id) {
		Emploi_id = emploi_id;
	}

	public String getSeance_id() {
		return Seance_id;
	}

	public void setSeance_id(String seance_id) {
		Seance_id = seance_id;
	} 
}
