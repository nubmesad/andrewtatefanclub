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
    
    public ResultSet validatePaperIDRetrieve(String title) {

        return review.searchpaperID(title);
  
    }
    
    
    public ResultSet viewAllCurrentBids(String reviewerId) {

        return review.currentBid(reviewerId);
  
    }
    
    public ResultSet retrievePapers() {
    	
        return review.viewPapers();
	}
 
	public boolean validateSubmitBid(String paperId, String reviewerId, String bidInfo) { 

    		return review.submitBid(paperId, reviewerId, bidInfo);
	}  
  
    
	public boolean validateReviewWorkload(String userId,String workload) {
    		return review.workload(userId, workload);
	}
}
