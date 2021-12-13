package Pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Model.Admin;
import application.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private ImageView btnClose;

	@FXML
	private Label btnForgot;

	@FXML
	private Button btnSignin;

	@FXML
	private Label lblErrors;

	@FXML
	private Button signupBtn;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private TextField txtUsername;

	@FXML
	private Label wrongLbl;

	@FXML
	void CLose_Login_CLick(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	void signIn_CLick(ActionEvent event) {
		File myObj = new File("Admin.txt");
		Scanner myReader;
		try {
			myReader = new Scanner(myObj);

			myReader.nextLine();
			String data = myReader.nextLine();
			String[] datas = data.split("/");
			System.out.println(data);
			Arrays.toString(datas);
			Admin admin = new Admin(datas[0], datas[1], datas[2], datas[3], datas[6], datas[7], datas[4],
					Store.deCrypt(datas[5]));
			if (txtUsername.getText().equals(admin.getPseudo()) && txtPassword.getText().equals(admin.getPassword())) {

				Node node = (Node) event.getSource();
				Stage stage = (Stage) node.getScene().getWindow();
				stage.close();
				Parent page1 = FXMLLoader.load(getClass().getResource("/Pages/mainwindow.fxml"));
				Scene scene1 = new Scene(page1, 850, 500);
				Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
				scene1.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				stage1.setScene(scene1);
				stage1.show();

			} else
				wrongLbl.setText("username/password incorrect");

		} catch (FileNotFoundException e1) {
			Store.afficherWarningAlert(e1.getMessage());
		} catch (IOException e) {
			Store.afficherWarningAlert(e.getMessage());
		}
	}

	@FXML
	void evaluez(InputMethodEvent event) {
		System.out.println("work");
		if (txtUsername.getText().length() != 8)
			btnSignin.setDisable(true);
	}

	@FXML
	void go_to_forget_password(MouseEvent event) {
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void signup_click(ActionEvent event) {
		try {
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			stage.close();
			Parent newPage = FXMLLoader.load(getClass().getResource("/Pages/SignUp.fxml"));
			Scene newScene = new Scene(newPage, 850, 470);
			Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			newScene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			newStage.setScene(newScene);
			newStage.show();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		File myObj = new File("Admin.txt");
		Scanner myReader;
		try {
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			myReader = new Scanner(myObj);
			int a = myReader.nextLine().charAt(0);
			System.out.println(a);
			int firstTime = a;
			if (firstTime == 48) {
				signupBtn.setDisable(false);
				btnSignin.setDisable(true);
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

}
