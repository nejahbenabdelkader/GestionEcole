package Model;

public class Admin extends Personne{
 private String questionSecret;
 private String reponseSecret;
 private String pseudo;
 private String password;

 public String getQuestionSecret() {
	return questionSecret;
}

public void setQuestionSecret(String questionSecret) {
	this.questionSecret = questionSecret;
}

public String getReponseSecret() {
	return reponseSecret;
}

public void setReponseSecret(String reponseSecret) {
	this.reponseSecret = reponseSecret;
}

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

@Override
public String toString() {
	return "Admin [questionSecret=" + questionSecret + ", reponseSecret=" + reponseSecret + ", pseudo=" + pseudo
			+ ", password=" + password + "]";
}

public Admin(String cIN, String prenom, String nom, String tel, String email, char sexe, String date_naissance2,String photo, String questionSecret, String reponseSecret, String pseudo, String password) {
	super(cIN, prenom, nom, tel, email, sexe, date_naissance2, photo);
	this.questionSecret = questionSecret;
	this.reponseSecret = reponseSecret;
	this.pseudo = pseudo;
	this.password = password;
}

public Admin(String cIN,String email, String nom, String prenom, String questionSecret, String reponseSecret, String pseudo, String password) {
	super(cIN,prenom,nom,"",email,"".charAt(0),"","");
	this.pseudo=pseudo;
	this.password=password;
	this.questionSecret=questionSecret;
	this.reponseSecret=reponseSecret;
}
}
