package Pages.emploi;

import java.io.IOException;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class list_emploiController {

    @FXML
    private TableColumn<?, ?> colonne_actions;

    @FXML
    private TableColumn<?, ?> colonne_capacite;

    @FXML
    private TableColumn<?, ?> colonne_capacite_classe;

    @FXML
    private TableColumn<?, ?> colonne_capacite_salle;

    @FXML
    private TableColumn<?, ?> colonne_niveau_classe;

    @FXML
    private TableColumn<?, ?> colonne_nom_classe;

    @FXML
    private TableColumn<?, ?> colonne_nom_salle;

    @FXML
    private TableColumn<?, ?> colonne_total_heures;

    @FXML
    private JFXComboBox<?> combobox_classe;

    @FXML
    private JFXComboBox<?> combobox_instituteur;

    @FXML
    private JFXComboBox<?> combobox_niveau;

    @FXML
    private JFXComboBox<?> combobox_salle;

    @FXML
    private TableView<?> tableview_emploi;

    @FXML
    void chercher_emploi(ActionEvent event) {

    }

    @FXML
    void goto_admin_main(ActionEvent event) {
    	try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent page1 = FXMLLoader.load(getClass().getResource("/Pages/mainwindow.fxml"));
			Scene scene1 = new Scene(page1, 850, 500);
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
    void goto_ajouter_emploi(ActionEvent event) {
    	try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent newPage = FXMLLoader.load(getClass().getResource("/Pages/emploi/add_emploi.fxml"));
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
    void supprimer_emploi(ActionEvent event) {

    }

    @FXML
    void supprimer_emplois(ActionEvent event) {

    }

    @FXML
    void user_selection(MouseEvent event) {

    }

}
