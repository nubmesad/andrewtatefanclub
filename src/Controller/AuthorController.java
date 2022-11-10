package Controller;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import Entity.Authors;
import Entity.Paper;
import Entity.User;


public class AuthorController 
{
	private Paper paper = new Paper();
	private Authors authors = new Authors();

    public ResultSet validateRetrieve(String paperId) 
    {
		if(paperId.matches(".*")) {

        return paper.search(paperId);
		}
		else {
			throw new IllegalArgumentException("Paper ID not found");
		}    
    }
    
	public ResultSet retrieveAuthorName() {
		return authors.authorNameComboBox();
	}
    
	public ResultSet retrievePaperID() {
		return paper.getLastPaperID();
	}
    
	public ResultSet retrieveUserTable() 
	{
			return paper.view();
	}
	public boolean validateUpdate(String paperId, String title,String content) 
	{
		if(paperId.matches("^[a-zA-Z0-9]*$") && title.matches("^[a-zA-Z0-9]*$")) 
		{
			return paper.updatePaper(paperId, title, content);
		}
		else 
		{
			throw new IllegalArgumentException("Invalid input format");
		}
	}
	
	
	
	public boolean validateSubmitPaper(String title,String content) {

		if(title.equals(""))
		{
			throw new IllegalArgumentException("Title cannot be empty.");
		}
		else if(content.equals(""))
		{
			throw new IllegalArgumentException("Content cannot be empty.");
		}
		else 
		{
    		return paper.create(title, content);
		}  
	}
	
	public boolean validateSubmitPaperAuthor(String paperId, String submittedId,String authorId) { 
		
    		return paper.createPA(paperId, submittedId, authorId);
	}  
	
	
    public ResultSet validateIDRetrieve(String username) {
		if(username.matches(".*")) {

        return authors.searchID(username);
		}
		else {
			throw new IllegalArgumentException("Name not found");
		}    
    }
    
    public ResultSet validateAuthorIDRetrieve(String name) {
 		if(name.matches(".*")) {

         return authors.searchAuthorID(name);
 		}
 		else {
 			throw new IllegalArgumentException("Name not found");
 		}    
     }
    
	public boolean validateUpdate(String userID, String username,String password,String email) {
		if(userID.matches("^[a-zA-Z0-9]*$") && username.matches("^[a-zA-Z0-9]*$")) {
			return authors.updateAuthor(userID,username, password, email);
		}
		else {
			throw new IllegalArgumentException("Invalid input format");
		}
	}

}