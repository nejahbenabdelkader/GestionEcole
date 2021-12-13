package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mysql.jdbc.ResultSet;

import Model.Etudiant;
import Model.Prof;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Store {
	private static String CLASSE_ID_CLICKED;
	private static String STUDENT_ID_CLICKED;
	private static Etudiant MODIFIED_STUDENT;

	public static Parent getActualPage() {
		return actualPage;
	}

	public static void setActualPage(Parent actualPage) {
		Store.actualPage = actualPage;
	}

	private static Parent actualPage;

	public Prof getProfWithCin(String prof_cin) throws SQLException {
		Connection con = BD.getConnection();
		PreparedStatement stmnt = con.prepareStatement("SELECT * FROM `enseignant` WHERE CIN=?");
		stmnt.setString(1, prof_cin);
		java.sql.ResultSet result = stmnt.executeQuery();
		result.next();
		Prof p = new Prof();
		p.setCIN(result.getString("CIN"));
		p.setNom(result.getString("NOM"));
		p.setPrenom(result.getString("PRENOM"));
		return p;
	}

	public static void SendEmail(String to, String messageText) throws MessagingException {
		try {
			String host = "smtp.gmail.com";
			String user = "najehbenabdelkader@gmail.com";
			String pass = "dkjyvaadyjfmmlmk";
			String from = "projetjava9@gmail.com";
			String subject = "[New order was added] ";
			boolean sessionDebug = false;

			Properties props = System.getProperties();

			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.required", "true");

			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			msg.setSentDate(new java.util.Date());
			msg.setText(messageText);
 			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("message send successfully");
		} catch (MessagingException ex) {
			Store.afficherWarningAlert(ex.getMessage());
		}

	}

	public static String crypt(String pass) {
		String result = "";
		int ascii = 0;
		for (int i = 0; i < pass.length(); i++) {
			ascii = (int) pass.charAt(i) + 21;
			result = result + (char) ascii;
		}
		return result;
	}

	public static String deCrypt(String pass) {
		String result = "";
		int ascii = 0;
		for (int i = 0; i < pass.length(); i++) {
			ascii = (int) pass.charAt(i) - 21;
			result = result + (char) ascii;
		}
		return result;
	}

	public static void afficherWarningAlert(String text) {
		Alert alert = new Alert(AlertType.WARNING, text);
		alert.setTitle("Error!!");
		alert.setHeaderText("");
		alert.show();

	}

	public static void afficherSuccesAlert(String text) {
		Alert alert = new Alert(AlertType.CONFIRMATION, text);
		alert.setHeaderText("");
		alert.setTitle("SUCCES !!");
		alert.show();

	}

	public static String getCLASSE_ID_CLICKED() {
		return CLASSE_ID_CLICKED;
	}

	public static void setCLASSE_ID_CLICKED(String cLASSE_ID_CLICKED) {
		CLASSE_ID_CLICKED = cLASSE_ID_CLICKED;
	}

	public static String getSTUDENT_ID_CLICKED() {
		return STUDENT_ID_CLICKED;
	}

	public static void setSTUDENT_ID_CLICKED(String sTUDENT_ID_CLICKED) {
		STUDENT_ID_CLICKED = sTUDENT_ID_CLICKED;
	}

	public static Etudiant getMODIFIED_STUDENT() {
		return MODIFIED_STUDENT;
	}

	public static void setMODIFIED_STUDENT(Etudiant mODIFIED_STUDENT) {
		MODIFIED_STUDENT = mODIFIED_STUDENT;
	}

}
