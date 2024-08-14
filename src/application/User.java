package application;

public class User {

	public String name, phone, username, password;

	public User(String name, String phone, String username, String password) {
		
		this.name = name;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}
	
	public User(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
