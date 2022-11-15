package Controller;

import java.sql.ResultSet;

import Entity.ConferenceChair;
import Entity.Reviewers;

public class ConferenceChairController {
	
	private ConferenceChair conference = new ConferenceChair();
	
    public ResultSet viewAllCurrentBids() {

        return conference.currentBid();
  
    }
    
    public ResultSet validateBidsDDL(String reviewId) {

        return conference.AllocateBidsDDL(reviewId);
        
    }
    
    public ResultSet getAllocatedPapersCount(String reviewId) {

        return conference.searchAllocatedPapersCount(reviewId);
        
    }
    
    public ResultSet getPaperIdList(String reviewerId) {

        return conference.autoAllocationSearchPaperId(reviewerId);
        
    }
    
    public ResultSet getPaperId(String title) {

        return conference.searchPaperID(title);
        
    }
    
    public ResultSet getReviewedPaperInfomation(String title) {

        return conference.searchReviewedPaperInfomation(title);
        
    }
    
    public Boolean insertAllocation(String paperId, String reviewerId) {

        return conference.manualAllocation(paperId,reviewerId);
        
    }
    
    public Boolean insertAllocationUpdateBidStatus(String paperId, String reviewerId) {

        return conference.manualAllocationUpdateBidsStatus(paperId,reviewerId);
        
    }
    
    public Boolean insertAllocationUpdateBidStatusFailed(String paperId) {

        return conference.manualAllocationUpdateBidsStatusToFailed(paperId);
        
    }
    
    public Boolean removeAllocatedPapers(String paperId) {

        return conference.deleteAllocatedPapers(paperId);
        
    }
    
    public ResultSet validateIDRetrieve(String username) {
		if(username.matches(".*")) {

        return conference.searchID(username);
		}
		else {
			throw new IllegalArgumentException("Name not found");
		}    
    }
    
    public ResultSet validateReviewerIDRetrieve(String name) {
		if(name.matches(".*")) {

        return conference.searchReviewerID(name);
		}
		else {
			throw new IllegalArgumentException("Name not found");
		}    
    }
    public ResultSet validateWorkload(String reviewId) {

        return conference.searchWorkload(reviewId);  
    }
    
    
    public ResultSet viewAllReviewedPapers() {

        return conference.getReviewedPapers();
  
    }
    
    public Boolean insertStatus(String paperId, String conferenceId, String choice) {

        return conference.submitPaperStatus(paperId,conferenceId,choice);
        
    }
    
}
