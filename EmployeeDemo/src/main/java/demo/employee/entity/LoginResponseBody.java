package demo.employee.entity;

public class LoginResponseBody {
	
	private String username;
	private String token;
	
	public LoginResponseBody() {
		
	}

	public LoginResponseBody(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
