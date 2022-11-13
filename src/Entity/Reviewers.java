package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reviewers {
	private String reviewerId;
	private String workload;

	//Constructors
	public Reviewers()
	{
	}
	
	
	public ResultSet viewPapers () {
		String sqlStatement = "SELECT * FROM papers";
		String[] parameters = {};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public Reviewers(String reviewerId, String workload)
	{
		this.reviewerId = reviewerId;
		this.workload = workload;
	}
	
	public boolean workload(String reviewerId, String workload )
	{
		String sqlStatement = "INSERT INTO reviewers (reviewId, workload) values (?,?) ON DUPLICATE KEY UPDATE workload = (?)";
		String[] parameters = {reviewerId, workload,workload};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	
	public boolean submitBid(String paperId, String reviewerId, String bidInfo )
	{
		String sqlStatement = "INSERT INTO bids (paperId, reviewerId, bidInfo, bidStatus) values (?,?,?,'Pending') ON DUPLICATE KEY UPDATE bidInfo = (?)";
		String[] parameters = {paperId, reviewerId, bidInfo, bidInfo};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	
	public ResultSet searchID (String username) {
		String sqlStatement = "SELECT * FROM users WHERE username=?";
		String[] parameters = {username};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet currentBid (String reviewerId) {
		String sqlStatement = "SELECT P.title FROM bids B JOIN papers P ON B.paperId = P.paperId WHERE B.bidInfo = 'Yes' AND B.reviewerId = (?);";
		String[] parameters = {reviewerId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	
	
	public ResultSet searchpaperID (String title) {
		String sqlStatement = "SELECT paperId FROM papers WHERE title = ?";
		String[] parameters = {title};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
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
