package Controller;

import java.sql.ResultSet;

import Entity.ConferenceChair;
import Entity.Reviewers;

public class ConferenceChairController {
	
	private ConferenceChair conference = new ConferenceChair();
	
    public ResultSet viewAllCurrentBids() {

        return conference.currentBid();
  
    }
    public ResultSet validateIDRetrieve(String username) {
		if(username.matches(".*")) {

        return conference.searchID(username);
		}
		else {
			throw new IllegalArgumentException("Name not found");
		}    
    }
    public ResultSet validateWorkload(String reviewId) {

        return conference.searchWorkload(reviewId);  
    }
}
