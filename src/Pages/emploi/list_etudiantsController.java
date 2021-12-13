package Pages.emploi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class list_etudiantsController {

    @FXML
    private TableColumn<?, ?> Sexe;

    @FXML
    private TableColumn<?, ?> cin;

    @FXML
    private TableColumn<?, ?> mail;

    @FXML
    private TableColumn<?, ?> nom;

    @FXML
    private TableColumn<?, ?> prenom;

    @FXML
    private TableView<?> tab_etudiant;

    @FXML
    private TableColumn<?, ?> tel;

    @FXML
    void back_to_main(ActionEvent event) {

    }

}