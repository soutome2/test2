package com.example.demo.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserInputForm {
	
	@NotBlank(message = "利用者IDを入力して下さい")
	private String eid;
	
	@NotBlank(message = "商品名を入力して下さい")
	private String ename;
	
	@NotBlank(message = "パスワードを入力して下さい")
	private String password;
	
	@NotBlank(message = "メールアドレスを入力して下さい")
	private String mailaddress;
	
	@NotBlank(message = "部署を入力して下さい")
	private String station;
	
	private String zip;
	
	private String address;
	
	private String tel;
	
	private Integer sendchk;
	
	private Integer possibleamt;
	
	public User getEntity() {
		User user = new User();
		user.setEid(eid);
		user.setEname(ename);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(password));
		user.setMailaddress(mailaddress);
		user.setStation(station);
		user.setZip(zip);
		user.setAddress(address);
		user.setTel(tel);
		user.setSendchk(sendchk);
		user.setPossibleamt(possibleamt);
		return user;
	}

}
