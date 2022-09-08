package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BddManager {

	private final String BDD_URL = "jdbc:mysql://localhost:3306/gestionressourcesuniversitaires";
	private Connection con;
	
	public BddManager() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		}catch(ClassNotFoundException e){
			
			e.printStackTrace();
			System.exit(-1);
		}
		
		
		try {
			con = DriverManager.getConnection(BDD_URL,"root","");
		}catch(SQLException e) {
			e.printStackTrace();
			System.exit(-2);
		}
		
	}
	
	public Connection getConnection() {
		return con ;
	}
	
	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
