package Controller;
import Entity.User;

public class UserLoginController {
	private User _user = new User();
	
	public String validateLogin(String username, String password, String accountType) {
		if(username.matches("^[a-zA-Z0-9]*$"))
			return _user.login(username, password, accountType);
		else {
			return "";
		}
	}
}
