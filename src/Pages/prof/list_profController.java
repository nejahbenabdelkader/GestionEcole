package Pages.prof;

import java.io.IOException;

import javax.swing.JOptionPane;

import Model.Etudiant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class list_profController {

	@FXML
	private TreeTableColumn<Etudiant, String> cin;

	@FXML
	private TreeTableColumn<Etudiant, String> date_naissance;

	@FXML
	private TreeTableColumn<Etudiant, String> email;

	@FXML
	private ImageView image;

	@FXML
	private TreeTableColumn<Etudiant, String> matiere;

	@FXML
	private TreeTableColumn<Etudiant, String> nom;

	@FXML
	private TreeTableColumn<Etudiant, String> prenom;

	@FXML
	private TreeTableColumn<Etudiant, String> salaire;

	@FXML
	private TreeTableColumn<Etudiant, String> tel;

	@FXML
	void back_to_main(MouseEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent page1;
			page1 = FXMLLoader.load(getClass().getResource("/Pages/mainwindow.fxml"));
			Scene scene1 = new Scene(page1, 850, 500);
			Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene1.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			stage1.setScene(scene1);
			stage1.show();
			System.out.println("es");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@FXML
	void add_prof(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent newPage = FXMLLoader.load(getClass().getResource("/Pages/prof/ajout_prof.fxml"));
			Scene newScene = new Scene(newPage, 600, 400);
			Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			newScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			newStage.setScene(newScene);
			newStage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	@FXML
	void changer_prof(ActionEvent event) {

	}

	@FXML
	void delete_prof(ActionEvent event) {

	}

}
