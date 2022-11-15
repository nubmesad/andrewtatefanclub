/**
 * 
 */
package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author User
 *
 */
public class ConferenceChair {

	private String conferenceId;
	private String bids;

	//Constructors
	public ConferenceChair()
	{
	}
	
	public ConferenceChair(String conferenceId, String bids)
	{
		this.conferenceId = conferenceId;
		this.bids = bids;
	}
	
	public ResultSet currentBid () {
		String sqlStatement = "SELECT P.title,U.name FROM bids B JOIN papers P ON B.paperId = P.paperId JOIN users U ON B.reviewerId = U.userId WHERE B.bidInfo = 'Yes' AND B.bidStatus = 'Pending'";
		String[] parameters = {};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet AllocateBidsDDL (String reviewId) {
		String sqlStatement = "SELECT P.title FROM bids B JOIN papers P ON B.paperId = P.paperId JOIN users U ON B.reviewerId = U.userId WHERE B.reviewerId = ? AND B.bidInfo = 'Yes' AND B.bidStatus = 'Pending'";
		String[] parameters = {reviewId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet searchAllocatedPapersCount (String reviewId) {
		String sqlStatement = "SELECT COUNT(`reviewerId`) FROM allocated_papers WHERE `reviewerId` = ?;";
		String[] parameters = {reviewId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet searchID (String username) {
		String sqlStatement = "SELECT * FROM users WHERE username=?";
		String[] parameters = {username};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet searchPaperID (String title) {
		String sqlStatement = "SELECT paperId FROM papers WHERE title=?";
		String[] parameters = {title};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet searchReviewerID (String name) {
		String sqlStatement = "SELECT * FROM users WHERE name=?";
		String[] parameters = {name};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet searchWorkload (String reviewId) {
		String sqlStatement = "SELECT workload FROM reviewers WHERE reviewId=?";
		String[] parameters = {reviewId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public boolean manualAllocation (String paperId, String reviewerId) {
		String sqlStatement = "INSERT INTO allocated_papers (`paperId`,`reviewerId`,`reviewStatus`) VALUES (?,?,'Not Reviewed') ";
		String[] parameters = {paperId, reviewerId};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	
	public boolean manualAllocationUpdateBidsStatus (String paperId, String reviewerId) {
		String sqlStatement = "UPDATE bids SET bidStatus = 'Success' WHERE `paperId` = ? AND `reviewerId` = ?"
				+ "";
		String[] parameters = {paperId, reviewerId};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	
	public boolean manualAllocationUpdateBidsStatusToFailed (String paperId) {
		String sqlStatement = "UPDATE bids SET bidStatus = 'Failed' WHERE `paperId` = ? AND `bidStatus` = 'Pending'";
		String[] parameters = {paperId};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	
	public ResultSet autoAllocationSearchPaperId (String reviewerId) {
		String sqlStatement = "SELECT `paperId` FROM `bids` WHERE reviewerId = ? AND bidStatus = 'Pending' AND bidInfo = 'Yes'";
		String[] parameters = {reviewerId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet getReviewedPapers() {
		String sqlStatement = "SELECT P.title FROM allocated_papers B JOIN papers P ON B.paperId = P.paperId WHERE B.reviewStatus = 'Reviewed'";
		String[] parameters = {};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet searchReviewedPaperInfomation(String title) {
		String sqlStatement = "SELECT R.*,P.title,U.name FROM allocated_papers A JOIN reviews R ON A.paperId = R.paperId JOIN papers P ON A.paperId = P.paperId JOIN users U ON A.reviewerId = U.userId WHERE A.reviewStatus = 'Reviewed' AND P.title = ?";
		String[] parameters = {title};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public boolean submitPaperStatus (String paperId, String conferenceId, String choice) {
		String sqlStatement = "INSERT INTO paper_status (`paperId`,`conferenceChairId`,`paperStatus`) VALUES (?,?,?) ";
		String[] parameters = {paperId, conferenceId, choice};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	
	
	public boolean deleteAllocatedPapers (String paperId) {
		String sqlStatement = "DELETE FROM `allocated_papers` WHERE `paperId` = ?";
		String[] parameters = {paperId};
		int rows = createUpdateHelper(sqlStatement, parameters);
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
