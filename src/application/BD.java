package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class BD {
	public static Connection getConnection() {
		Connection conn = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionecole?" + "user=root&password=");
			System.out.println("Connected Succesfully !");

		} catch (SQLException ex) {
			
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	};

	public static  ResultSet getResult(String query) {
		ResultSet result = null;
		try {
			Connection conn = getConnection();
			Statement stmnt = conn.createStatement();
			result = stmnt.executeQuery(query);
			System.out.println("Query excuted succfully");
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		return result;
	};
	
	public static void insertData(String query) {
		try {
			Connection conn=getConnection();
			Statement stmnt=conn.createStatement();
			stmnt.executeUpdate(query);
			System.out.println("succes inserted !");
			conn.close();
		}
		catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
	};
	
	public static ResultSet executeQuery(String query,String[] values) throws SQLException {
		Connection con=getConnection();
		java.sql.PreparedStatement stmnt=con.prepareStatement(query);
		for (int i = 1; i < values.length+1; i++) {
			stmnt.setString(i, values[i-1]);
		}
		System.out.println(stmnt.toString());
		ResultSet result=stmnt.executeQuery();
		return result ;
	}

}
