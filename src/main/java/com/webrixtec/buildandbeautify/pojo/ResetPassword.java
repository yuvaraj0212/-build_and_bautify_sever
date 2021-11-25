package com.webrixtec.buildandbeautify.pojo;

import javax.validation.constraints.NotNull;

public class ResetPassword {
	
	@NotNull(message="password must not to be null")
	private String password;
	@NotNull(message="confirm password must not to be null")
	private String confirmPassword;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	

}
