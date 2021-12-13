package Pages.eleve;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.SQLException;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXComboBox;
import com.mysql.jdbc.PreparedStatement;

import application.BD;
import application.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ajout_eleveController implements Initializable {

	@FXML
	private Text succesLabel;

	@FXML
	private TextField cin;

	@FXML
	private DatePicker date_naissance;

	@FXML
	private TextField email;

	@FXML
	private JFXComboBox<String> id_classe;

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

	@FXML
	private JFXComboBox<String> sexe;

	@FXML
	private TextField tel;

	@FXML
	void add_eleve(ActionEvent event) {
		if (checkValues()) {
			Connection con = BD.getConnection();
			String testQuery = "SELECT COUNT('CIN') AS NUMBER FROM ETUDIANT WHERE CIN=? ;";
			String updateQuery="UPDATE `classe` SET `Nombre_etudiants`=Nombre_etudiants+1 WHERE `Identifiant`=?";
			String query = "INSERT INTO `etudiant`(`CIN`, `Nom`, `Prenom`, `Sexe`, `Photo`, `Tel`, `Email`, `ID_CLASSE` , `date_naissance`) VALUES (?,?,?,?,?,?,?,?,?) ;";
			java.sql.PreparedStatement testStmnt;
			try {
				testStmnt = con.prepareStatement(testQuery);

				testStmnt.setString(1, cin.getText());
				System.out.println(testStmnt.toString());
				java.sql.ResultSet result = testStmnt.executeQuery();
				result.next();
				if (Integer.parseInt(result.getString("NUMBER")) == 0) {
					java.sql.PreparedStatement updateStmnt=con.prepareStatement(updateQuery);
					updateStmnt.setString(1, id_classe.getValue());
					updateStmnt.execute();
					java.sql.PreparedStatement stmnt = con.prepareStatement(query);
					stmnt.setString(1, cin.getText());
					stmnt.setString(2, nom.getText());
					stmnt.setString(3, prenom.getText());
					stmnt.setString(4, sexe.getValue().substring(0, 1));
					stmnt.setString(5, "");
					stmnt.setString(6, tel.getText());
					stmnt.setString(7, email.getText());
					stmnt.setString(8, id_classe.getValue());
					stmnt.setString(9, date_naissance.getValue().toString());

					Store.SendEmail(email.getText(), "welcome to our platform");
					stmnt.execute();
					Store.afficherSuccesAlert("Student Added Succesfully ");
				}

				else {
					succesLabel.setText("Student alredy exist !");
				}

			} catch (SQLException e) {
				Store.afficherWarningAlert(e.getMessage());
			} catch (MessagingException e) {
				Store.afficherWarningAlert(e.getMessage());

			}

		}

	}

	@FXML
	void back_to_eleves(MouseEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent newPage = FXMLLoader.load(getClass().getResource("/Pages/eleve/list_eleves.fxml"));
			Scene newScene = new Scene(newPage, 900, 400);
			Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			newScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			newStage.setScene(newScene);
			newStage.show();
		} catch (IOException ex) {
			// Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null,
			// ex);
			ex.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String query = "SELECT `Identifiant` FROM `classe` ;";
		try {
			java.sql.ResultSet result = BD.getResult(query);
			while (result.next()) {

				id_classe.getItems().add(result.getString("Identifiant"));
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		sexe.getItems().addAll("Homme", "Femme");

	}

	public boolean checkValues() {
		if (Pattern.matches("[0-9]+", cin.getText()) && (cin.getText().length() == 8)) {
			if (date_naissance.getValue().isBefore(
					Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
				if(email.getText().isEmpty()&&tel.getText().isBlank()&&!Pattern.matches("[0-9]+", nom.getText())) {
					Store.afficherWarningAlert("Field Are Emplty !!");
					return false;
				}
				else return true;
			} else {
				Store.afficherWarningAlert("Invalid Date !");
				return false;
			}

		} else {
			Store.afficherWarningAlert("CIN must be 8 numbers");
			return false;
		}

	}

}