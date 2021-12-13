package Pages.eleve;



import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.PreparedStatement;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;




public class list_elevesController implements  Initializable{
	
	private static ObservableList<Etudiant> studentsList = FXCollections.observableArrayList();
	

	
	@FXML
    private TableView<Etudiant> tab_student;

    @FXML
    private TableColumn<Etudiant, String> cin;

    @FXML
    private TableColumn<Etudiant, String> date_naissance;

    @FXML
    private TableColumn<Etudiant, String> email;

    @FXML
    private TableColumn<Etudiant, String> id_classe;

    @FXML
    private TableColumn<Etudiant, String> nom;

    @FXML
    private TableColumn<Etudiant, String> prenom;

    @FXML
    private TableColumn<Etudiant, String> sex;

    @FXML
    private TableColumn<Etudiant, String> tel;
    
    @FXML
    private JFXButton btnChange;

    @FXML
    private JFXButton btnDelete;


    @FXML
    void add_student(ActionEvent event) {
    	try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent page1 = FXMLLoader.load(getClass().getResource("/Pages/eleve/ajout_eleve.fxml"));
			Scene scene1 = new Scene(page1, 700, 450);
			Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene1.getStylesheets()
					.add(getClass().getResource("/application/application.css").toExternalForm());
			stage1.setScene(scene1);
			stage1.show();
		} catch (IOException ex) {
			// Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null,
			// ex);
			ex.printStackTrace();
		}

    }

    @FXML
    void back_to_main(MouseEvent event) {
    	try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent page1 = FXMLLoader.load(getClass().getResource("/Pages/mainwindow.fxml"));
			Scene scene1 = new Scene(page1, 850, 500);
			Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene1.getStylesheets()
					.add(getClass().getResource("/application/application.css").toExternalForm());
			stage1.setScene(scene1);
			stage1.show();
		} catch (IOException ex) {
			// Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null,
			// ex);
			ex.printStackTrace();
		}
    }

    @FXML
    void delete_student(ActionEvent event) throws SQLException {
    	System.out.println(Store.getSTUDENT_ID_CLICKED());
    	java.sql.Connection con = BD.getConnection();
		String query = "DELETE FROM `etudiant` WHERE cin=? ;";
		String updateQuery="UPDATE `classe` SET `Nombre_etudiants`=Nombre_etudiants-1 WHERE `Identifiant`=?";
		java.sql.PreparedStatement stmnt = con.prepareStatement(query);
		java.sql.PreparedStatement updateStmnt=con.prepareStatement(updateQuery);
		stmnt.setString(1, Store.getSTUDENT_ID_CLICKED());
		updateStmnt.setString(1, Store.getCLASSE_ID_CLICKED());
		stmnt.execute();
		updateStmnt.execute();
		studentsList.removeIf((e)->e.getCIN()==Store.getSTUDENT_ID_CLICKED());
		con.close();

    }

    @FXML
    void modify_student(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cin.setCellValueFactory(new PropertyValueFactory<>("CIN"));
		prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		date_naissance.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		id_classe.setCellValueFactory(new PropertyValueFactory<>("NumeroClasse"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		sex.setCellValueFactory(new PropertyValueFactory<>("sexe"));
		studentsList.clear();
		String query="SELECT * FROM ETUDIANT ;" ;
		java.sql.ResultSet result=BD.getResult(query) ;
		try {
			while (result.next()) {
				Etudiant et=new Etudiant(result.getString("CIN"),result.getString("prenom"),result.getString("nom"),result.getString("tel"), result.getString("email"), result.getString("sexe").charAt(0), result.getString("date_naissance"),null,result.getString("ID_CLASSE"));				
				et.toString();
				studentsList.add(et);
				System.out.println();
				
			}
			tab_student.setItems(studentsList);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		tab_student.setRowFactory(tv -> {
			TableRow<Etudiant> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Etudiant clickedRow = row.getItem();
					Store.setSTUDENT_ID_CLICKED(clickedRow.getCIN());
					Store.setCLASSE_ID_CLICKED(clickedRow.getNumeroClasse());
					btnDelete.setDisable(false);
					btnChange.setDisable(false);
					System.out.println(Store.getSTUDENT_ID_CLICKED());

				}
			});
			return row;
		});
		
	}

	public static ObservableList<Etudiant> getStudentsList() {
		return studentsList;
	}

	public static void setStudentsList(ObservableList<Etudiant> studentsList) {
		list_elevesController.studentsList = studentsList;
	}

    

}
