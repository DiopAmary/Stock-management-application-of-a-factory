package produitsDataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private String db = "dioppp___store";
	//private String db = "compte_exam";
	private String username = "dioppp___";
	private String userPwd = "17698@Dopk";
	private String url = "jdbc:mysql://localhost:3306/"+db;
	
	private static Connection connection = null;
	
	private Connexion(){
		try {
			connection = DriverManager.getConnection(url, username, userPwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnexion() {
		if(connection == null) {
			 new Connexion();
		}
		return connection;
	}
}
