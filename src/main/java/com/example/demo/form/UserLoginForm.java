package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginForm {
	
	@NotBlank(message = "利用者名を入力して下さい")
	private String ename;
	
	@NotBlank(message = "パスワードを入力して下さい")
	private String password;

}
