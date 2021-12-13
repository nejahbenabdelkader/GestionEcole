package Pages.prof;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXComboBox;
import com.mysql.jdbc.ResultSet;

import Model.Etudiant;
import application.BD;
import application.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ajout_profController implements Initializable{
	
	private static ObservableList<String> matiereList = FXCollections.observableArrayList();
	
	@FXML
    private TextField password;
	
	@FXML
    private TextField pseudo;

	@FXML
	private TextField cin;

	@FXML
	private Label cinLabel;

	@FXML
	private DatePicker date_naissance;

	@FXML
	private TextField email;

	@FXML
	private Label emailLabel;

	@FXML
	private JFXComboBox<String> matiere_enseigne;

	@FXML
	private TextField nom;

	@FXML
	private Label nomLabel;

	@FXML
	private TextField prenom;

	@FXML
	private Label prenomLabel;

	@FXML
	private TextField salaire;

	@FXML
	private JFXComboBox<String> sexe;

	@FXML
	private Text succesLabel;

	@FXML
	private TextField tel;

	@FXML
	private Label telLabel;

	@FXML
	void back_to_profs(MouseEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent page1 = FXMLLoader.load(getClass().getResource("/Pages/prof/list_prof.fxml"));
			Scene scene1 = new Scene(page1, 950, 400);
			Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene1.getStylesheets()
					.add(getClass().getResource("/application/application.css").toExternalForm());
			stage1.setScene(scene1);
			stage1.show();
		} catch (IOException ex) {
			
			
		}

	}

	@FXML
	void add_prof(ActionEvent event) {
		if (checkValues()) {
			Connection con = BD.getConnection();
			String testQuery = "SELECT COUNT('CIN') AS NUMBER FROM `enseignant` WHERE CIN=? ;";
			String query = "INSERT INTO `enseignant`(`CIN`, `NOM`, `PRENOM`, `SEXE`,`EMAIL`, `TEL`, `MATIERE`, `Salaire`,`password`) VALUES (?,?,?,?,?,?,?,?,?) ;";
			java.sql.PreparedStatement testStmnt;
			try {
				testStmnt = con.prepareStatement(testQuery);
				testStmnt.setString(1, cin.getText());
				java.sql.ResultSet result = testStmnt.executeQuery();
				result.next();
				if (Integer.parseInt(result.getString("NUMBER")) == 0) {
					java.sql.PreparedStatement stmnt = con.prepareStatement(query);
					stmnt.setString(1, cin.getText());
					stmnt.setString(2, nom.getText());
					stmnt.setString(3, prenom.getText());
					stmnt.setString(4, sexe.getValue().substring(0, 1));
					stmnt.setString(5, email.getText());
					stmnt.setString(6, tel.getText());
					stmnt.setString(7, matiere_enseigne.getValue());
					stmnt.setString(8, salaire.getText());
					stmnt.setString(9, password.getText());
Store.SendEmail(email.getText(), "mar7be biik !");
					stmnt.execute();
					JOptionPane.showMessageDialog(null, "Prof Added Succefully !");
				}

				else {
					JOptionPane.showMessageDialog(null, "Prof alredy exist !");
				}

			} catch (SQLException e) {
				Store.afficherWarningAlert(e.getMessage());
			} catch (MessagingException e) {
				Store.afficherWarningAlert(e.getMessage());
			}
		}
		

	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String query="SELECT  `NOM` FROM `mariere` ;";
		ResultSet result=(ResultSet) BD.getResult(query);
		
		try {
			while(result.next()) {
			  matiereList.add(result.getString("NOM"));
			  matiere_enseigne.setItems(matiereList);
			}
		} catch (SQLException e) {
			
		}
		sexe.getItems().addAll("Homme","Femme");
		
		
	}
	
	public boolean checkValues() {
		if (Pattern.matches("[0-9]+", cin.getText()) && (cin.getText().length() == 8)) {
				
				return true;
			

		} else {
			Store.afficherWarningAlert("CIN must be 8 numbers");
			return false;
		}

	}

}