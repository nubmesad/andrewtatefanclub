package Controller;
import java.sql.ResultSet;

import Entity.Paper;

public class AuthorController 
{
	private Paper paper = new Paper();
	
    public ResultSet validateRetrieve(String title) 
    {
		if(title.matches(".*")) {

        return paper.search(title);
		}
		else {
			throw new IllegalArgumentException("Title not found");
		}    
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
}