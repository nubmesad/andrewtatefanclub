package Controller;

import java.sql.ResultSet;

import Entity.Reviewers;

public class ReviewerController {
	
	private Reviewers review = new Reviewers();
	
    public ResultSet validateIDRetrieve(String username) {
		if(username.matches(".*")) {

        return review.searchID(username);
		}
		else {
			throw new IllegalArgumentException("Name not found");
		}    
    }
    
	public boolean validateReviewWorkload(String userId,String workload) {
    		return review.workload(userId, workload);
	}
}
