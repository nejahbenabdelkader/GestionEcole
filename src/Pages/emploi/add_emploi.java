package Pages.emploi;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.mysql.jdbc.ResultSet;

import Model.Class;
import Model.Etudiant;
import Model.Matiere;
import Model.Prof;
import Model.Salle;
import Model.Séance;
import application.BD;
import application.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Control;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class add_emploi implements Initializable {

	private String selectedEmploi = "";

	String[] seances = { "lundi_1", "lundi_2", "lundi_3", "mardi_2", "mercredi_1", "mercredi_2", "mercredi_3",
			"mercredi_4", "jeudi_1", "jeudi_2", "jeudi_3", "jeudi_4", "vendredi_1", "vendredi_2", "vendredi_3",
			"vendredi_4", "samedi_1", "samedi_2", "lundi_4", "mardi_3", "mardi_1", "mardi_4" };
	private ObservableList<String> profList = FXCollections.observableArrayList();

	private ObservableList<String> salleList = FXCollections.observableArrayList();

	private String profSelected = "";
	private String salleSelected = "";
	private String matiereSelected = "";
	private String seanceSelected = "";

	@FXML
	private GridPane tableau;

	@FXML
	private JFXButton action;

	@FXML
	private Rectangle h10h12;

	@FXML
	private Rectangle h14h16;

	@FXML
	private Rectangle h16h18;

	@FXML
	private Rectangle h8h10;

	@FXML
	private Rectangle jeudi_0;

	@FXML
	private Pane jeudi_1;

	@FXML
	private Pane jeudi_2;

	@FXML
	private Pane jeudi_3;

	@FXML
	private Pane jeudi_4;

	@FXML
	private JFXListView<String> listview_matieres;

	@FXML
	private JFXListView<String> listview_salles;

	@FXML
	private Rectangle lundi_0;

	@FXML
	private Pane lundi_1;

	@FXML
	private Pane lundi_2;

	@FXML
	private Pane lundi_3;

	@FXML
	private Pane lundi_4;

	@FXML
	private Rectangle mardi_0;

	@FXML
	private Pane mardi_1;

	@FXML
	private Pane mardi_2;

	@FXML
	private Pane mardi_3;

	@FXML
	private Pane mardi_4;

	@FXML
	private Text matiere;

	@FXML
	private Rectangle mercredi_0;

	@FXML
	private Pane mercredi_1;

	@FXML
	private Pane mercredi_2;

	@FXML
	private Pane mercredi_3;

	@FXML
	private Pane mercredi_4;

	@FXML
	private Text prof;

	@FXML
	private Text salle;

	@FXML
	private Rectangle samedi_0;

	@FXML
	private Pane samedi_1;

	@FXML
	private Pane samedi_2;

	@FXML
	private Rectangle vendredi_0;

	@FXML
	private Pane vendredi_1;

	@FXML
	private Pane vendredi_2;

	@FXML
	private Pane vendredi_3;

	@FXML
	private Pane vendredi_4;

	@FXML
	private JFXComboBox<String> emplois_id;

	@FXML
	void ajouter_emploi(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Nouveau Emploi?");
		dialog.setContentText("enter the new emploi id: ");

		Optional<String> result = dialog.showAndWait();
		/*
		 * if(Pattern.matches("[0-9]+", result.get())) {
		 * JOptionPane.showMessageDialog(null, "match"); System.out.println("match"); }
		 * else { System.out.println("5arref"); }
		 */
		emplois_id.getItems().add(result.get());
	}

	@FXML
	void check_available(MouseEvent event) {
		ObservableList<String> profavailable = profList;
		ObservableList<String> salleavailable = salleList;
		ObservableList<String> profoccupe = FXCollections.observableArrayList();
		ObservableList<String> salleoccupe = FXCollections.observableArrayList();
		String seance_id = ((Pane) event.getSource()).getId();
		setSeanceSelected(seance_id);
		String query = "SELECT COUNT(*) AS NUMBER ,`SALLE`, `PROF` FROM `séance` WHERE Seance_id=?  ;";
		try {
			Connection db = BD.getConnection();
			PreparedStatement stmnt = db.prepareStatement(query);
			stmnt.setString(1, seance_id);
			java.sql.ResultSet result = stmnt.executeQuery();
			result.next();
			System.out.println(result.getString("NUMBER"));
			System.out.println(seance_id);
			if (Integer.parseInt(result.getString("NUMBER")) != 0) {
				do {
					System.out.println("1");
					System.out.println(result.getString("SALLE"));
					System.out.println(result.getString("Prof"));
					System.out.println(this.getProfWithCin(result.getString("Prof")));
					System.out.println(this.getSalleWith(result.getString("SALLE")));
					salleoccupe.add(this.getSalleWith(result.getString("SALLE")));
					profoccupe.add(this.getProfWithCin(result.getString("Prof")));
				} while (result.next());
				salleavailable = salleList.stream().filter(x -> !salleoccupe.contains(x))
						.collect(Collectors.toCollection(FXCollections::observableArrayList));
				;
				profavailable = profList.stream().filter(x -> !profoccupe.contains(x))
						.collect(Collectors.toCollection(FXCollections::observableArrayList));
				;
				listview_matieres.setItems(profavailable);
				listview_salles.setItems(salleavailable);
				salleavailable.forEach(x -> System.out.println(x));
				System.out.println("------------------");
				salleoccupe.forEach(x -> System.out.println(x));
			} else {
				listview_matieres.setItems(profList);
				listview_salles.setItems(salleList);
			}
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.WARNING);
			EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					alert.setAlertType(AlertType.WARNING);
					alert.setHeaderText(ex.getMessage());
					alert.show();
				}
			};
		}

	}

	public String getSelectedEmploi() {
		return selectedEmploi;
	}

	public void setSelectedEmploi(String selectedEmploi) {
		this.selectedEmploi = selectedEmploi;
	}

	public JFXComboBox<String> getEmplois_id() {
		return emplois_id;
	}

	public void setEmplois_id(JFXComboBox<String> emplois_id) {
		this.emplois_id = emplois_id;
	}

	@FXML
	void check_conflicts(ActionEvent event) {

	}

	@FXML
	void goto_admin_main(ActionEvent event) {
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
		} catch (IOException ex) {
			Alert alert = new Alert(AlertType.WARNING);
			EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					alert.setAlertType(AlertType.WARNING);
					alert.setHeaderText(ex.getMessage());
					alert.show();
				}
			};
		}
	}

	@FXML
	void reset_all_cells(MouseEvent event) {

		for (String seance : seances) {
			try {
				System.out.println(seance);
				Pane parent = (Pane) tableau.lookup("#" + seance);
				Text mat = (Text) parent.lookup("#" + "matiere");
				Text prof = (Text) parent.lookup("#" + "prof");
				Text salle = (Text) parent.lookup("#" + "salle");
				mat.setText("---------------------------------------");
				prof.setText("---------------------------------------");
				salle.setText("-----------------");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	@FXML
	void reset_cell(ActionEvent event) {

		JFXButton button = (JFXButton) event.getSource();
		Pane parent = (Pane) button.getParent();
		Text mat = (Text) parent.lookup("#" + "matiere");
		Text prof = (Text) parent.lookup("#" + "prof");
		Text salle = (Text) parent.lookup("#" + "salle");
		mat.setText("---------------------------------------");
		prof.setText("---------------------------------------");
		salle.setText("-----------------");
		Connection con = BD.getConnection();
		String query = "DELETE FROM `séance` WHERE `EmploiId`=? AND `Seance_id`=? ;";
		try {
			PreparedStatement stmnt = con.prepareStatement(query);
			stmnt.setString(1, emplois_id.getValue());
			stmnt.setString(2, parent.getId());
			stmnt.toString();
			stmnt.execute();
			Alert alert = new Alert(AlertType.WARNING);
			EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					alert.setAlertType(AlertType.CONFIRMATION);
					alert.setHeaderText("Deleted succesfully !");
					alert.show();
				}
			};
		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.WARNING);
			EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					alert.setAlertType(AlertType.WARNING);
					alert.setHeaderText(ex.getMessage());
					alert.show();
				}
			};
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// afficher l'emploi dans la page lorsque il est selectionée
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					update_emploi();
				} catch (IOException e1) {

				}
			}
		};
		emplois_id.setOnAction(event);
		// charger les ids des emplois ,les profs et les salles
		java.sql.ResultSet rs1 = BD.getResult("SELECT `numero_salle`, `capacite` FROM `salle` ");
		java.sql.ResultSet rs2 = BD.getResult("SELECT  `NOM`, `PRENOM`, `MATIERE` FROM `enseignant` ");
		java.sql.ResultSet rs3 = BD.getResult("SELECT DISTINCT `EmploiId` FROM `séance`");

		try {
			while (rs1.next()) {
				salleList.add(rs1.getString("numero_salle") + "/" + rs1.getString("capacite"));
			}
			while (rs2.next()) {
				profList.add(rs2.getString("PRENOM") + " " + rs2.getString("NOM") + "/" + rs2.getString("MATIERE"));
			}
			while (rs3.next()) {
				emplois_id.getItems().add(rs3.getString("EmploiId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		profList.forEach(x -> listview_matieres.getItems().add(x));
		salleList.forEach(x -> listview_salles.getItems().add(x));
		listview_salles.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setSalleSelected(listview_salles.getSelectionModel().getSelectedItem().split("/")[0]);
				setValueToSeance();
			}
		});
		listview_matieres.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setProfSelected(listview_matieres.getSelectionModel().getSelectedItem().split("/")[0]);
				setMatiereSelected(listview_matieres.getSelectionModel().getSelectedItem().split("/")[1]);
				setValueToSeance();
			}
		});
	}

	void update_emploi() throws IOException {
		Connection connection = BD.getConnection();
		try {

			PreparedStatement stmnt = connection
					.prepareStatement("SELECT COUNT(*) AS NUMBER,séance.* FROM `séance` WHERE EmploiId=?");

			stmnt.setString(1, emplois_id.getValue());
			java.sql.ResultSet result = stmnt.executeQuery();
			result.next();
			System.out.println(result.getString("NUMBER"));
			if (Integer.parseInt(result.getString("NUMBER")) != 0) {
				do {
					setSeanceValue(new Séance(result.getString("SALLE"), result.getString("PROF"),
							result.getString("Matiere"), result.getString("EmploiId"), result.getString("Seance_id")));
				} while (result.next());
			}

		} catch (SQLException ex) {
			Alert alert = new Alert(AlertType.WARNING);
			EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					alert.setAlertType(AlertType.WARNING);
					alert.setHeaderText(ex.getMessage());
					alert.show();
				}
			};

		}

	}

	private void setSeanceValue(Séance séance) throws IOException {
		System.out.println("inside setseance value");
		Pane pane = (Pane) tableau.lookup("#" + séance.getSeance_id());
		Text mat = (Text) pane.lookup("#" + "matiere");
		Text prof = (Text) pane.lookup("#" + "prof");
		Text salle = (Text) pane.lookup("#" + "salle");
		System.out.println(mat.getText());
		System.out.println(séance.toString());
		mat.setDisable(false);
		mat.setLayoutX(mat.getLayoutX() * 0.99);
		mat.setText(séance.getMatiereEtudié());
		prof.setText(séance.getProfEnseigné().getPrenom() + " " + séance.getProfEnseigné().getNom());
		salle.setText(séance.getSalle());
		System.out.println(mat.getText());

	}

	public String getProfWithCin(String prof_cin) throws SQLException {
		Connection con = BD.getConnection();
		PreparedStatement stmnt = con
				.prepareStatement("SELECT  `NOM`, `PRENOM`,`MATIERE`FROM `enseignant` WHERE CIN=?");
		stmnt.setString(1, prof_cin);
		java.sql.ResultSet result = stmnt.executeQuery();
		result.next();
		String s = result.getString("PRENOM") + " " + result.getString("NOM") + "/" + result.getString("MATIERE");
		return s;
	}

	public String getSalleWith(String num_salle) throws SQLException {
		Connection con = BD.getConnection();
		PreparedStatement stmnt = con.prepareStatement("SELECT  `capacite` FROM `salle` WHERE numero_salle=?;");
		stmnt.setString(1, num_salle);
		java.sql.ResultSet result = stmnt.executeQuery();
		result.next();
		String s = num_salle + "/" + result.getString("capacite");
		return s;
	}

	public void setValueToSeance() {
		System.out.println(getSeanceSelected());
		System.out.println(getProfSelected());
		System.out.println(getMatiereSelected());
		System.out.println(getSalleSelected());
		Pane pane = (Pane) tableau.lookup("#" + getSeanceSelected());
		Text mat = (Text) pane.lookup("#" + "matiere");
		Text prof = (Text) pane.lookup("#" + "prof");
		Text salle = (Text) pane.lookup("#" + "salle");
		mat.setText(getMatiereSelected());
		prof.setText(getProfSelected());
		salle.setText(getSalleSelected());
	}

	@FXML
	void confirm_update(ActionEvent event) {
		int num = 0;
		try {
			for (String seance : seances) {
				System.out.println(seance);
				Pane parent = (Pane) tableau.lookup("#" + seance);
				Text mat = (Text) parent.lookup("#" + "matiere");
				Text prof = (Text) parent.lookup("#" + "prof");
				Text salle = (Text) parent.lookup("#" + "salle");
				if (!(mat.getText().contains("-") || salle.getText().contains("-"))) {
					String query = "INSERT INTO `séance`(`SALLE`, `PROF`, `Matiere`, `EmploiId`, `Seance_id`) VALUES (?,?,?,?,?)";
					Connection con = BD.getConnection();
					PreparedStatement stmnt = con.prepareStatement(query);
					stmnt.setString(1, salle.getText());
					stmnt.setString(2, getCinWithName(prof.getText()));
					stmnt.setString(3, mat.getText());
					stmnt.setString(4, emplois_id.getValue());
					stmnt.setString(5, seance);
					stmnt.execute();
					mat.setText("---------------------------------------");
					prof.setText("---------------------------------------");
					salle.setText("-----------------");
					num++;

				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Store.afficherWarningAlert(e.getMessage());
		}
		Store.afficherSuccesAlert("Added " + num + "Seances to emploi " + emplois_id.getValue());

	}

	public String getSalleSelected() {
		return salleSelected;
	}

	public void setSalleSelected(String salleSelected) {
		this.salleSelected = salleSelected;
	}

	public String getProfSelected() {
		return profSelected;
	}

	public void setProfSelected(String profSelected) {
		this.profSelected = profSelected;
	}

	public String getMatiereSelected() {
		return matiereSelected;
	}

	public void setMatiereSelected(String matiereSelected) {
		this.matiereSelected = matiereSelected;
	}

	public String getSeanceSelected() {
		return seanceSelected;
	}

	public void setSeanceSelected(String seanceSelected) {
		this.seanceSelected = seanceSelected;
	}

	public String getCinWithName(String prenomNom) throws SQLException {
		String query = "SELECT COUNT(*) AS NUMBER , `CIN` FROM `enseignant` WHERE `PRENOM`=? AND `NOM`=? ;";

		String[] values = prenomNom.split(" ");
		values[1] = values[1].split("/")[0];
		java.sql.ResultSet result = BD.executeQuery(query, values);
		result.next();
		if (Integer.parseInt(result.getString("NUMBER")) == 1) {
			return result.getString("CIN");
		} else
			System.out.println(result.getString("NUMBER"));
		throw new SQLException("Entity dosen,t Exist !");

	}

}
