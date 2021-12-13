package Pages;

import java.io.IOException;

import javax.swing.JOptionPane;

import application.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainwindowController {

	

	@FXML
	private AnchorPane anchor;

	@FXML
	void click_absence(ActionEvent event) {

	}

	@FXML
	void click_apprs(ActionEvent event) {

	}

	@FXML
	void click_gclasses(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent newPage = FXMLLoader.load(getClass().getResource("/Pages/classe/list_classes.fxml"));
			Scene newScene = new Scene(newPage, 700, 400);
			Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			newScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			newStage.setScene(newScene);
			newStage.show();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	@FXML
	void click_geleves(ActionEvent event) {
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

	@FXML
	void click_gemplois(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent newPage = FXMLLoader.load(getClass().getResource("/Pages/emploi/add_emploi.fxml"));
Store.setActualPage(newPage);
			Scene newScene = new Scene(newPage, 1200, 600);
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

	@FXML
	void exit(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void click_ginst(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent page1 = FXMLLoader.load(getClass().getResource("/Pages/prof/list_prof.fxml"));
			Scene scene1 = new Scene(page1, 950, 400);
			Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene1.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			stage1.setScene(scene1);
			stage1.show();
		} catch (IOException ex) {
			// Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null,
			// ex);
			ex.printStackTrace();
		}

	}

	@FXML
	void click_gmatiere(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent newPage = FXMLLoader.load(getClass().getResource("/Pages/matiere/listMatiere.fxml"));
			Scene newScene = new Scene(newPage, 1200, 500);
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

	@FXML
	void click_gnotes(ActionEvent event) {
		/**
		 * try { Node node = (Node) event.getSource(); Stage stage = (Stage)
		 * node.getScene().getWindow(); stage.close(); Parent newPage =
		 * FXMLLoader.load(getClass().getResource("/Pages/note/listNote.fxml")); Scene
		 * newScene = new Scene(newPage, 1200, 500); Stage newStage = (Stage) ((Node)
		 * event.getSource()).getScene().getWindow();
		 * newScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		 * newStage.setScene(newScene); newStage.show(); } catch (IOException ex) { //
		 * Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, //
		 * ex); ex.printStackTrace(); }
		 **/
	}

	@FXML
	void click_gsalles(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent newPage = FXMLLoader.load(getClass().getResource("/Pages/salle/list_salle.fxml"));
			Scene newScene = new Scene(newPage, 1200, 500);
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

}