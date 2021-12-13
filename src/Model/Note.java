package Model;

public class Note {
	private int ControleContinu;
	private int DS;
	private int Examen;
	private String Remarques;

	public int getControleContinu() {
		return ControleContinu;
	}

	public void setControleContinu(int controleContinu) {
		ControleContinu = controleContinu;
	}

	public int getDS() {
		return DS;
	}

	public void setDS(int dS) {
		DS = dS;
	}

	public int getExamen() {
		return Examen;
	}

	public void setExamen(int examen) {
		Examen = examen;
	}

	public String getRemarques() {
		return Remarques;
	}

	public void setRemarques(String remarques) {
		Remarques = remarques;
	}

	public Note(int controleContinu, int dS, int examen, String remarques) {
		super();
		ControleContinu = controleContinu;
		DS = dS;
		Examen = examen;
		Remarques = remarques;
	}

}
