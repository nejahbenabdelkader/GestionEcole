package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import application.BD;

public class S�ance {
	private String Salle;
	private Prof ProfEnseign�;
	private String MatiereEtudi�;
    private String Emploi_id;
    @Override
	public String toString() {
		return "S�ance [Salle=" + Salle + ", ProfEnseign�=" + ProfEnseign� + ", MatiereEtudi�=" + MatiereEtudi�
				+ ", Emploi_id=" + Emploi_id + ", Seance_id=" + Seance_id + "]";
	}

	private String Seance_id;

	public S�ance(String salle, String profEnseign�, String string, String emploi_id, String seance_id) throws SQLException {
		Salle = salle;
		MatiereEtudi� = string;
		Emploi_id = emploi_id;
		Seance_id = seance_id;
		ProfEnseign�=new Prof();
		ResultSet result=BD.getResult("SELECT  `NOM`, `PRENOM`, `SEXE`, `EMAIL`, `TEL` FROM `enseignant` WHERE CIN="+profEnseign�);
		result.next();
		ProfEnseign�.setCIN(profEnseign�);
		ProfEnseign�.setNom(result.getString("NOM"));
		ProfEnseign�.setPrenom(result.getString("PRENOM"));
		
	}

	public String getSalle() {
		return Salle;
	}

	public void setSalle(String salle) {
		Salle = salle;
	}

	public Prof getProfEnseign�() {
		return ProfEnseign�;
	}

	public void setProfEnseign�(Prof profEnseign�) {
		ProfEnseign� = profEnseign�;
	}

	public String getMatiereEtudi�() {
		return MatiereEtudi�;
	}

	public void setMatiereEtudi�(String matiereEtudi�) {
		MatiereEtudi� = matiereEtudi�;
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
