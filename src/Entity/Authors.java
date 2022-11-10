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
		String sqlStatement = "SELECT `fname` , `lname` FROM authors";
		String [] parameters = {};
		ResultSet rs = queryHelper(sqlStatement, parameters);
		return rs;
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

