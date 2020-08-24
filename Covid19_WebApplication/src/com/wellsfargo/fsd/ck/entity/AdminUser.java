package com.wellsfargo.fsd.ck.entity;

public class AdminUser {
	private String username;
    private String password;
    public boolean valid;
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
	public boolean isValid() {
		valid=false;
		if(username.equals("admin") && password.equals("admin")){
			setValid(true);
		}
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
    

}
