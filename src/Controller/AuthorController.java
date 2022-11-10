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

    public ResultSet validateRetrieve(String title) 
    {
		if(title.matches(".*")) {

        return paper.search(title);
		}
		else {
			throw new IllegalArgumentException("Title not found");
		}    
    }
    
	public ResultSet retrieveAuthorName() {
		return authors.authorNameComboBox();
	}
    
	public ResultSet retrievePaperTitle() {
		return paper.PapersTitleComboBox();
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
	
	

}