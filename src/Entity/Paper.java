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


public class Paper 
{
	private String paperId;
	private String title;
	private String content;
	//Constructors
	public Paper()
	{
	}
	
	public Paper(String paperId, String title, String content)
	{
		this.paperId = paperId;
		this.title = title;
		this.content = content;
	}
	public boolean create(String title, String content)
	{
		String sqlStatement = "INSERT INTO papers (title, content) VALUES(?, ?)";
		String[] parameters = {title, content};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	
	public boolean createPA(String paperId, String submittedId,String authorId)
	{
		String sqlStatement = "INSERT INTO paper_authors (paperId, submittedId, authorId) VALUES(?,?,?)";
		String[] parameters = {paperId, submittedId,authorId};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	public boolean updatePaper (String paperId, String title, String content)
	{
		String sqlStatement = "UPDATE Paper SET title=?, content=? WHERE paperId=?";
		String[] parameters = {title, content, paperId};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	public ResultSet search (String paperId) 
	{
		String sqlStatement = "SELECT title FROM papers WHERE paperId=?";
		String[] parameters = {paperId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	public ResultSet view() 
	{
		String sqlStatement = "SELECT * FROM papers";
		String [] parameters = {};
		ResultSet rs = queryHelper(sqlStatement, parameters);
		return rs;
	}
	
	public ResultSet getLastPaperID() {
		String sqlStatement = "SELECT paperId , title from papers ORDER by paperId DESC LIMIT 1";
		String [] parameters = {};
		ResultSet rs = queryHelper(sqlStatement, parameters);
		return rs;
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