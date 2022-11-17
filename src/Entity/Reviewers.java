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
	
	public boolean deleteCurrentBids(String paperId, String reviewerId)
	{
		String sqlStatement = "UPDATE bids SET `bidInfo` = 'No' WHERE `paperId`=? AND `reviewerId`=?";
		String[] parameters = {paperId,reviewerId};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	public boolean deleteReview(String paperId)
	{
		String sqlStatement = "DELETE from reviews WHERE paperId = ?";
		String[] parameters = {paperId};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
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
	public boolean submitReview(String paperId, String rating, String reviews, String reviewerId )
	{
		String sqlStatement = "INSERT INTO reviews (paperId, rating, review, reviewerId) values (?,?,?,?)";
		String[] parameters = {paperId, rating, reviews,reviewerId};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	public boolean submitComment(String paperId, String userId, String comment, String date )
	{
		String sqlStatement = "INSERT INTO reviews_comment (paperId, userId, comment, date) values (?,?,?,?)";
		String[] parameters = {paperId, userId, comment, date};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	
	public boolean updateAllocatedReviewed (String paperId) {
		String sqlStatement = "UPDATE allocated_papers SET reviewStatus = 'Reviewed' WHERE `paperId` = ?";
		String[] parameters = {paperId};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	
	public boolean updateAllocatedNotReviewed (String paperId) {
		String sqlStatement = "UPDATE allocated_papers SET reviewStatus = 'Not Reviewed' WHERE `paperId` = ?";
		String[] parameters = {paperId};
		int rows = createUpdateHelper(sqlStatement, parameters);
		return rows>0;
	}
	
	public boolean updateReview (String rating, String review, String paperId) {
		String sqlStatement = "UPDATE reviews SET `rating` = ?, `review` = ? WHERE `paperId` = ?";
		String[] parameters = {rating,review,paperId};
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
		String sqlStatement = "SELECT P.title FROM bids B JOIN papers P ON B.paperId = P.paperId WHERE B.bidInfo = 'Yes' AND B.reviewerId = (?) AND B.bidStatus = 'Pending';";
		String[] parameters = {reviewerId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	
	public ResultSet AllocatedBids (String reviewerId) {
		String sqlStatement = "SELECT P.title, P.content FROM allocated_papers B JOIN papers P ON B.paperId = P.paperId WHERE B.reviewerId = ? AND B.reviewStatus = 'Not Reviewed'";
		String[] parameters = {reviewerId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	public ResultSet viewReviewedPaper (String reviewerId) {
		String sqlStatement = "SELECT P.title FROM reviews B JOIN papers P ON B.paperId = P.paperId WHERE B.reviewerId = ?";
		String[] parameters = {reviewerId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet viewContent (String title) {
		String sqlStatement = "SELECT content from papers WHERE title = ?";
		String[] parameters = {title};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	public ResultSet viewAuthor (String title) {
		String sqlStatement = "SELECT U.name FROM paper_authors B JOIN papers P ON B.paperId = P.paperId JOIN users U ON B.authorId = U.userId WHERE P.title = ?";
		String[] parameters = {title};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	public ResultSet viewReview (String paperId) {
		String sqlStatement = "SELECT review, rating from reviews WHERE paperId = ?";
		String[] parameters = {paperId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet viewComment (String paperId) {
		String sqlStatement = "SELECT userId,comment,date FROM `reviews_comment` WHERE paperId = ?";
		String[] parameters = {paperId};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	
	public ResultSet searchpaperID (String title) {
		String sqlStatement = "SELECT paperId FROM papers WHERE title = ?";
		String[] parameters = {title};
		ResultSet result = queryHelper(sqlStatement, parameters);
		return result;
	}
	public ResultSet reviewsComboBox() {
		String sqlStatement = "SELECT P.title FROM reviews B JOIN papers P ON B.paperId = P.paperId";
		String [] parameters = {};
		ResultSet rs = queryHelper(sqlStatement, parameters);
		return rs;
	}
	
	public ResultSet viewOtherReviews(String title) {
		String sqlStatement = "SELECT * FROM reviews B JOIN papers P ON B.paperId = P.paperId WHERE P.title = ?";
		String [] parameters = {title};
		ResultSet rs = queryHelper(sqlStatement, parameters);
		return rs;
	}
	
	public ResultSet viewBidsResult (String reviewId) {
		String sqlStatement = "SELECT B.paperId, P.title, B.bidInfo, B.bidStatus FROM bids B JOIN papers P ON B.paperId = p.paperId WHERE B.reviewerId = ? AND B.bidInfo = 'Yes' AND B.bidStatus = 'Success' OR B.reviewerId = ? AND B.bidInfo = 'Yes' AND B.bidStatus = 'Pending'";
		String[] parameters = {reviewId, reviewId};
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
