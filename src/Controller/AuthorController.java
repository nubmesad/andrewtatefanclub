package Controller;
import java.sql.ResultSet;

import Entity.User;

public class AuthorController {
	private User user = new User();
	
    public ResultSet validateRetrieve(String username) {
		if(username.matches(".*")) {

        return user.search(username);
		}
		else {
			throw new IllegalArgumentException("Name not found");
		}    
    }
    
	public boolean validateRegister(String username, String password, String accountType) {
		if(username.matches("^[a-zA-Z0-9]*$") && password.matches("^[a-zA-Z0-9]*$")) {
			if(user.isUsernameExist(username)) {
				throw new IllegalArgumentException("Username already Exist");
			}
			else {
				return user.register(username,password,accountType);
			}
		}
		else {
			throw new IllegalArgumentException("Invalid input format");
		}
	}
	
	public ResultSet retrieveUserTable() {
			return user.view();
	}
	public boolean validateUpdate(String userID, String username,String password,String accountType) {
		if(userID.matches("^[a-zA-Z0-9]*$") && username.matches("^[a-zA-Z0-9]*$")) {
			return user.updateInfo(userID,username, password, accountType);
		}
		else {
			throw new IllegalArgumentException("Invalid input format");
		}
	}
}