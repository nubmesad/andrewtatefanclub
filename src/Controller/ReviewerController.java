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
    
    public ResultSet retrieveContent(String title) {
    	
        return review.viewContent(title);
	}
    
    public ResultSet retrieveAuthor(String title) {
    	
        return review.viewAuthor(title);
	}
    
    public ResultSet retrieveAllocatedBids(String reviewerId) {
    	
        return review.AllocatedBids(reviewerId);
	}
    
    public ResultSet retrieveReviewedPapers(String reviewerId) {
    	
        return review.viewReviewedPaper(reviewerId);
	}
    
    public ResultSet retrieveReviews(String paperId) {
    	
        return review.viewReview(paperId);
	}
    
    public ResultSet retrieveComments(String paperId) {
    	
        return review.viewComment(paperId);
	}

    public ResultSet retrieveReviewsPaperId() {
    	
        return review.reviewsComboBox();
	}
    public ResultSet retrieveOtherReviews(String title) {
    	
        return review.viewOtherReviews(title);
	}
 
	public boolean validateSubmitBid(String paperId, String reviewerId, String bidInfo) { 

    		return review.submitBid(paperId, reviewerId, bidInfo);
	}  
	
	public boolean validateSubmitReview(String paperId, String rating, String reviews, String reviewerId) { 

			return review.submitReview(paperId, rating, reviews, reviewerId);

	}
	public boolean validateSubmitComment(String paperId, String userId, String comment, String date) { 

		return review.submitComment(paperId, userId, comment, date);

}
	public boolean validateUpdateStatus(String paperId) { 

		return review.updateAllocatedReviewed(paperId);

	}
	public boolean validateUpdateStatus2(String paperId) { 

		return review.updateAllocatedNotReviewed(paperId);

	}
	
	public boolean validateDeleteReview(String paperId) { 

		return review.deleteReview(paperId);

	}
	public boolean validateDeleteCurrentBids(String paperId, String reviewerId) { 

		return review.deleteCurrentBids(paperId,reviewerId);

	}
	public boolean validateUpdateReview(String rating, String reviews, String paperId) { 

		return review.updateReview(rating,reviews,paperId);

	}
  
	public boolean validateReviewWorkload(String userId,String workload) {
    		return review.workload(userId, workload);
	}
	
    public ResultSet getBidsResult(String reviewId) {
    	
        return review.viewBidsResult(reviewId);
	}
	
}
