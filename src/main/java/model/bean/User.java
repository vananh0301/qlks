package model.bean;

public class User {
	private String username;
	private String password;
	private String role;
	private String hoten;
	private String sdt;

	public User(String username, String password, String role, String hoten, String sdt) {
		this.username = username;
		this.password = password;
		this.role = role;
		this.hoten = hoten;
		this.sdt = sdt;
	}

	public User() {}

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
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {this.sdt = sdt;}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
