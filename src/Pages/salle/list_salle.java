package Pages.salle;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class list_salle {

    @FXML
    private JFXTextField capacite;

    @FXML
    private TableColumn<?, ?> colonne_capacite;

    @FXML
    private TableColumn<?, ?> colonne_cocher;

    @FXML
    private TableColumn<?, ?> colonne_date_creation;

    @FXML
    private TableColumn<?, ?> colonne_identifiant;

    @FXML
    private TableColumn<?, ?> colonne_modifier;

    @FXML
    private TableColumn<?, ?> colonne_nom;

    @FXML
    private TableColumn<?, ?> colonne_type;

    @FXML
    private JFXTextField dateS;

    @FXML
    private JFXTextField nom;

    @FXML
    private TableView<?> table_salle;

    @FXML
    private JFXTextField type;

    @FXML
    void goto_admin_main(ActionEvent event) {

    }

    @FXML
    void goto_ajouter_salle(ActionEvent event) {

    }

    @FXML
    void goto_lister_salle(ActionEvent event) {

    }

    @FXML
    void supprimer_salle(ActionEvent event) {

    }

    @FXML
    void supprimer_salles(ActionEvent event) {

    }

    @FXML
    void user_selection(MouseEvent event) {

    }

}

