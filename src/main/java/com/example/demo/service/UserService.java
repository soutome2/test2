package com.example.demo.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.entity.User;
import com.example.demo.form.UserLoginForm;
import com.example.demo.form.UserUpdateForm;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	public User getByEid(UserUpdateForm userUpdateForm, BindingResult result) {

		Optional<User> optionalUser = userRepository.findById(userUpdateForm.getEid());

		if (optionalUser.isEmpty()) {
			result.addError(new FieldError(
					result.getObjectName(), "eid", "存在しない利用者IDです。"));
			return null;
		}
		return optionalUser.get();
	}

	/**/
	public User getByEname(UserLoginForm userLoginForm, BindingResult result) {

		Optional<User> optionalUser = userRepository.findByEname(userLoginForm.getEname());
		
		if (optionalUser.isEmpty()) {
			result.addError(new FieldError(
					result.getObjectName(), "ename", "存在しない利用者名です。"));
			return null;
		}
		
		String rawPassword = userLoginForm.getPassword(); // ユーザーが入力した平文のパスワード

        // データベースから取得したハッシュ化されたパスワード
		User check = optionalUser.get();
        String storedPassword = check.getPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // パスワードを比較して認証する
        if (!encoder.matches(rawPassword, storedPassword)) {
        	result.addError(new FieldError(
					result.getObjectName(), "password", "パスワードが合致しません。"));
			return null;
		}
        return optionalUser.get();

	}
	/**/

}
