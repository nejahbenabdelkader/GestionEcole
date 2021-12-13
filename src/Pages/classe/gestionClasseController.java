package Pages.classe;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;


import Model.Class;
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
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class gestionClasseController implements javafx.fxml.Initializable {

	private static ObservableList<Class> classList = FXCollections.observableArrayList();

	@FXML
	private JFXButton btnStudent;

	@FXML
	private JFXButton btnTeachers;

	@FXML
	private Button AddBtn;

	@FXML
	private JFXButton DltBtn;

	@FXML
	private TextField txtFilliere;

	@FXML
	private TextField txtIdentifiant;

	 @FXML
	 private MenuButton list_emploi;

	@FXML
	private TextField txtNiveau;

	@FXML
	private Text textError;

	@FXML
	private TableColumn<Class, String> Emp_Col;

	@FXML
	private TableColumn<Class, String> F_Col;

	@FXML
	private TableColumn<Class, String> ID_Column;

	@FXML
	private TableColumn<Class, String> NB_Col;

	@FXML
	private TableColumn<Class, String> Niv_Col;

	@FXML
	private TableView<Class> tabClasse;

	@FXML
	void ajouter_classe(ActionEvent event) throws SQLException {
		if((txtIdentifiant.getText()=="")||(txtNiveau.getText()=="")||(txtFilliere.getText()=="")) {
			textError.setText("Some Field Are Empty");
		}else {
			
		
		
		
		java.sql.Connection con = BD.getConnection();
		String testQuery = "SELECT COUNT(`Identifiant`) AS NUMBER FROM `classe` WHERE Identifiant=? ;";
		java.sql.PreparedStatement testStmnt = con.prepareStatement(testQuery);
		testStmnt.setString(1, txtIdentifiant.getText());
		ResultSet result = testStmnt.executeQuery();
		result.next();
		if (Integer.parseInt(result.getString("NUMBER"))==0) {
		System.out.println();
		String query = "INSERT INTO `classe`(`Identifiant`, `Niveau`, `Filliere`, `Nombre_etudiants`, `EmploiId`) VALUES (?,?,?,0,?);";
		java.sql.PreparedStatement stmnt = con.prepareStatement(query);
		stmnt.setString(1, txtIdentifiant.getText());
		stmnt.setString(2, txtNiveau.getText());
		stmnt.setString(3, txtFilliere.getText());
		stmnt.setString(4, "1");
		stmnt.execute();
		classList.add(new Class(txtIdentifiant.getText(), txtFilliere.getText(), txtNiveau.getText(), 0, "1"));
		tabClasse.setItems(classList);
		textError.setText("Class added succefully !");
		textError.setStyle("-fx-text-fill: green;");
		txtIdentifiant.setText("");
		txtNiveau.setText("");
		txtFilliere.setText("");
		}
		else {
			textError.setText("Class alredy exist !");
			textError.setStyle("-fx-text-fill: red;");
		}
		con.close();
		}
	}

	@FXML
	void back_to_main(MouseEvent event) {
		System.out.println("element get pushed");
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
	void delete_classe(ActionEvent event) throws SQLException {
		java.sql.Connection con = BD.getConnection();
		String query = "DELETE FROM `classe` WHERE Identifiant=? ;";
		java.sql.PreparedStatement stmnt = con.prepareStatement(query);
		stmnt.setString(1, Store.getCLASSE_ID_CLICKED());
		stmnt.execute();
		classList.removeIf((e)->e.getId()==Store.getCLASSE_ID_CLICKED());
		con.close();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ID_Column.setCellValueFactory(new PropertyValueFactory<>("id"));
		F_Col.setCellValueFactory(new PropertyValueFactory<>("Filliere"));
		Niv_Col.setCellValueFactory(new PropertyValueFactory<>("Niveau"));
		NB_Col.setCellValueFactory(new PropertyValueFactory<>("NombreEtudiants"));
		Emp_Col.setCellValueFactory(new PropertyValueFactory<>("EmploiDuTemps"));
		classList.clear();
		String query = "select * from classe ;";
		ResultSet result = BD.getResult(query);
		try {
			while (result.next()) {
				classList.add(new Class(result.getString("Identifiant"), result.getString("Filliere"),
						result.getString("Niveau"), Integer.parseInt(result.getString("Nombre_etudiants")),
						result.getString("emploiId")));
				tabClasse.setItems(classList);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//when click on row the class id get registered 
		tabClasse.setRowFactory(tv -> {
			TableRow<Class> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Class clickedRow = row.getItem();
					Store.setCLASSE_ID_CLICKED(clickedRow.getId());
					btnStudent.setDisable(false);
					btnTeachers.setDisable(false);
					DltBtn.setDisable(false);

				}
			});
			return row;
		});
		
		

	}

	@FXML
	void see_students(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent page1 = FXMLLoader.load(getClass().getResource("/Pages/classe/list_etudiants.fxml"));
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
	void see_teachers(ActionEvent event) {
		
	}

}
