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
}

