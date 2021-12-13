module FinaleEcole {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires com.jfoenix;
	requires java.sql;
	requires javafx.base;
	requires java.desktop;
	requires mysql.connector;
	requires javax.mail;
	
	
	

	opens Pages to javafx.fxml;
	exports Pages to javafx.fxml;

	opens Pages.eleve to javafx.fxml;
	exports Pages.eleve to javafx.fxml;

	//opens Pages.note to javafx.fxml;
	//exports Pages.note to javafx.fxml;

	opens Pages.emploi to javafx.fxml ,javafx.controls;
	exports Pages.emploi to javafx.fxml, javafx.controls;
	
	opens Pages.matiere to javafx.fxml ;
	exports Pages.matiere to javafx.fxml;
	
	opens Pages.salle to javafx.fxml ;
	exports Pages.salle to javafx.fxml;
	
	// exports Pages.absence to javafx.fxml;
	opens Pages.classe to javafx.fxml;
	 exports Pages.classe to javafx.fxml;
	 
	 opens Pages.prof to javafx.fxml ;
	 exports Pages.prof to javafx.fxml ;
	 
	 opens Model to javafx.base ;

	opens application to javafx.graphics, javafx.fxml;
}
