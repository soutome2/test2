package com.example.demo.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateForm {
	
	@NotBlank(message = "利用者IDを入力して下さい")
	private String eid;
	
	@NotBlank(message = "パスワードを入力して下さい")
	private String password;
	
	@NotBlank(message = "部署を入力して下さい")
	private String station;
	
	private boolean chkPassword;
	private boolean chkStation;
	
	public User getEntity() {
		User user = new User();
		user.setEid(eid);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(password));
		user.setStation(station);
		return user;
	}

}
