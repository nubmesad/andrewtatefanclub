package Controller;
import java.sql.ResultSet;

import Entity.User;

public class AdminController {
	private User user = new User();
	
    public ResultSet validateRetrieve(String username) {
		if(username.matches(".*")) {

        return user.search(username);
		}
		else {
			throw new IllegalArgumentException("Name not found");
		}    
    }
    
	public boolean validateRegister(String userId, String username, String password, String email, String name, String accountType) {
		if(username.matches("^[a-zA-Z0-9]*$") && password.matches("^[a-zA-Z0-9]*$")) {
			if(user.isUsernameExist(username)) {
				throw new IllegalArgumentException("Username already Exist");
			}
			else {
				return user.register(userId,username,password,name,email,accountType);
			}
		}
		else {
			throw new IllegalArgumentException("Invalid input format");
		}
	}
	
	public ResultSet retrieveUserTable() {
			return user.view();
	}
	
	public boolean validateUpdate(String userID, String username,String password,String name, String email,String accountType) {
		if(userID.matches("^[a-zA-Z0-9]*$") && username.matches("^[a-zA-Z0-9]*$")) {
			return user.updateInfo(userID,username, password, name, email, accountType);
		}
		else {
			throw new IllegalArgumentException("Invalid input format");
		}
	}
}

