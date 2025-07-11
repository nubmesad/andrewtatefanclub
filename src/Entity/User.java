package Entity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class User {
	private String userID;
	private String username;
	private String password;
	private String email;
	private String accountType;

	public User(){}
	
	public User(String userID, String username, String password, String email, String accountType){
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
		this.accountType = accountType;
	}
	
	
	public String login (String username, String password, String accountType) {
		try {
			String sqlStatement = "SELECT * FROM users WHERE username=? AND password=? AND accountType=?";
			String[] parameters = {username, password, accountType};

			ResultSet result = queryHelper(sqlStatement, parameters);

			//retrieve user name from result
			while(result != null && result.next()) {
				return result.getString("username");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public boolean register (String userId, String username, String password, String name,String email, String accountType) {
			String sqlStatement = "INSERT INTO users (`userId`,`username`, `password`, `email`, `name`, `accountType`) VALUES (?,?,?,?,?,?)";
			String[] parameters = {userId, username, password, name, email, accountType};
			int rows = createUpdateHelper(sqlStatement, parameters);
			return rows>0;
	}
	
	public boolean isUsernameExist(String username) {
		try {
			String sqlStatement = "SELECT * FROM users WHERE username=?";
			String [] parameters = {username};
			ResultSet result = queryHelper(sqlStatement,parameters);
			
			//retrieve user name from result
			while(result != null && result.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public ResultSet search (String username) {
		String sqlStatement = "SELECT * FROM users WHERE username=?";
		String[] parameters = {username};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet view() {
			String sqlStatement = "SELECT * FROM users";
			String [] parameters = {};
			ResultSet rs = queryHelper(sqlStatement, parameters);
			return rs;
	}

	public boolean updateInfo(String userID, String username, String password, String name, String email, String accountType) {
		String sqlStatement = "UPDATE users SET username = ?, password = ?, name = ?, email = ?, accountType=? WHERE userID=?";
		String[] parameters = {username,password,name,email,accountType,userID};
		int rows = createUpdateHelper(sqlStatement,parameters);
		return rows>0;
	}
	//set up db connection
	private Connection dbConnection() {
		
	    Connection con = null;
	    String url = "jdbc:mysql://localhost:3306/AndrewTateFanClub";
	    String username = "root";
	    String password = "";
	    
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();	
		}
		return con;
	}
	
	private ResultSet queryHelper(String sqlStatement, String[] parameters) {
		Connection connection = dbConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(sqlStatement);
			for(int i = 0; i < parameters.length; i++) {
				statement.setString((i+1), parameters[i]);
			}
			return statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private int createUpdateHelper(String sqlStatement, String[] parameters) {
		Connection connection = dbConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(sqlStatement);
			for(int i = 0; i < parameters.length; i++) {
				statement.setString((i+1), parameters[i]);
			}
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}

