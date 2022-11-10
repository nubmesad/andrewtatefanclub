package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authors {
	private String authorId;
	private String fname;
	private String lname;
	
	public Authors()
	{
	}
	
	public Authors(String authorId, String fname, String lname)
	{
		this.authorId = authorId;
		this.fname = fname;
		this.lname = lname;
	}
	
	
	public ResultSet authorNameComboBox() {
		String sqlStatement = "SELECT `name`, `userId` FROM users WHERE `accountType` = 'Author'";
		String [] parameters = {};
		ResultSet rs = queryHelper(sqlStatement, parameters);
		return rs;
	}
	
	
	public ResultSet searchID (String username) {
		String sqlStatement = "SELECT * FROM users WHERE username=?";
		String[] parameters = {username};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet searchAuthorID (String name) {
		String sqlStatement = "SELECT userId FROM users WHERE name=?";
		String[] parameters = {name};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	public ResultSet searchTitle (String title) {
		String sqlStatement = "SELECT * FROM papers WHERE title=?";
		String[] parameters = {title};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public boolean updateAuthor(String userID, String username, String password, String email) {
		String sqlStatement = "UPDATE users SET username = ?, password = ?, email = ? WHERE userID=?";
		String[] parameters = {username,password,email,userID};
		int rows = createUpdateHelper(sqlStatement,parameters);
		return rows>0;
	}

	
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
	
	private ResultSet queryHelper(String sqlStatement, String[] parameters) 
	{
		Connection connection = dbConnection();

		try 
		{
			PreparedStatement statement = connection.prepareStatement(sqlStatement);
			for(int i = 0; i < parameters.length; i++) 
			{
				statement.setString((i+1), parameters[i]);
			}
			return statement.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	private int createUpdateHelper(String sqlStatement, String[] parameters) 
	{
		Connection connection = dbConnection();

		try 
		{
			PreparedStatement statement = connection.prepareStatement(sqlStatement);
			for(int i = 0; i < parameters.length; i++) 
			{
				statement.setString((i+1), parameters[i]);
			}
			return statement.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return 0;
		}
	}
}

