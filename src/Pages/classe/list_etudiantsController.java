package Pages.classe;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import Model.Etudiant;
import application.BD;
import application.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class list_etudiantsController  implements javafx.fxml.Initializable{
  
	private static ObservableList<Etudiant> studentsList = FXCollections.observableArrayList();
	
    @FXML
    private TableColumn<Etudiant, String> Sexe;

    @FXML
    private TableColumn<Etudiant, String> cin;

    @FXML
    private TableColumn<Etudiant, String> mail;

    @FXML
    private TableColumn<Etudiant, String> nom;

    @FXML
    private TableColumn<Etudiant, String> prenom;

    @FXML
    private TableView<Etudiant> tab_etudiant;

    @FXML
    private TableColumn<Etudiant, String> tel;

    @FXML
    void back_to_main(ActionEvent event) {
    	try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent newPage = FXMLLoader.load(getClass().getResource("/Pages/classe/list_classes.fxml"));
			Scene newScene = new Scene(newPage, 700	, 400);
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
		studentsList.clear();
		cin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		Sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		Connection con=BD.getConnection();
		String query="SELECT * FROM ETUDIANT WHERE ID_CLASSE=? ;" ;
		java.sql.PreparedStatement stmnt;
		try {
			stmnt = con.prepareStatement(query);
		
		stmnt.setString(1, Store.getCLASSE_ID_CLICKED());
		ResultSet result =stmnt.executeQuery();
		while(result.next()) {
			studentsList.add(new Etudiant(result.getString("CIN"), result.getString("PRENOM"),result.getString("NOM")
					, result.getString("TEL"),result.getString("Email"), result.getString("sexe").charAt(0),null, "",
			Store.getCLASSE_ID_CLICKED()));
		}
		tab_etudiant.setItems(studentsList);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ObservableList<Etudiant> getStudentsList() {
		return studentsList;
	}

	public static void setStudentsList(ObservableList<Etudiant> studentsList) {
		list_etudiantsController.studentsList = studentsList;
	}

}