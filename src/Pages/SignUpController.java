package Pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXComboBox;

import application.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

	@FXML
	private ImageView btnClose;

	@FXML
	private TextField cin;

	@FXML
	private TextField email;

	@FXML
	private TextField nom;

	@FXML
	private TextField password;

	@FXML
	private TextField prenom;

	@FXML
	private TextField pseudo;

	@FXML
	private JFXComboBox<String> secretQuestion;

	@FXML
	private TextField secretResponse;

	@FXML
	void CLose_Login_CLick(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void evaluez(ActionEvent event) {

	}

	@FXML
	void signUp_click(ActionEvent event) {
		try {
			
			FileWriter myWriter = new FileWriter("Admin.txt",false);
			String donnees=cin.getText()+"/"+email.getText()+"/"+nom.getText()+"/"+prenom.getText()+"/"+pseudo.getText()+"/"+Store.crypt(password.getText())+"/"+secretQuestion.getValue()+"/"+secretResponse.getText();
			myWriter.write("1\n");
			myWriter.write(donnees);
			myWriter.close();
			Store.afficherSuccesAlert("Welcome to Our Platforme");
				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
				Parent newPage = FXMLLoader.load(getClass().getResource("/Pages/login.fxml"));
				Scene newScene = new Scene(newPage, 850,500);
				Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				newScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				newStage.setScene(newScene);
				newStage.show();
			
		} catch (IOException e) {
			Store.afficherWarningAlert(e.getMessage());
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		secretQuestion.getItems().add("What is your name?");
		
	}

}
