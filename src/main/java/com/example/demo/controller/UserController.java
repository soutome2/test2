package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.form.UserInputForm;
import com.example.demo.form.UserUpdateForm;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class UserController {
	
	private final UserRepository userRepository;
	private final UserService userService;
	
	@GetMapping("/userList")
	public ModelAndView showuserList(ModelAndView mv) {
		List<User> userList = userRepository.findAll();
		mv.addObject("userList", userList);
		mv.setViewName("userList");
		return mv;
	}
	
	@GetMapping("/userInputForm")
	public String showForm(UserInputForm userInputForm) {
		return "userInputForm";
	}
	
	@PostMapping("/userResist")
	public String regist(@ModelAttribute @Validated UserInputForm userInputForm, BindingResult result) {
		if(!result.hasErrors()) {
			User user = userInputForm.getEntity();
			userRepository.saveAndFlush(user);
			return "redirect:/userList";
		} else {
			return "userInputForm";
		}
	}
	
	@GetMapping("/userUpdateForm")
	public String showForm(UserUpdateForm userUpdateForm) {
		return "userUpdateForm";
	}
	
	@PostMapping("/userUpdate")
	public String update(@ModelAttribute @Validated UserUpdateForm userUpdateForm, BindingResult result) {
		User originalUser = userService.getByEid(userUpdateForm, result);
		
		if(!result.hasErrors()) {
			User newUser = userUpdateForm.getEntity();
			newUser.setEname(originalUser.getEname());
			newUser.setMailaddress(originalUser.getMailaddress());
			newUser.setZip(originalUser.getZip());
			newUser.setAddress(originalUser.getAddress());
			newUser.setTel(originalUser.getTel());
			newUser.setSendchk(originalUser.getSendchk());
			newUser.setPossibleamt(originalUser.getPossibleamt());
			if(!userUpdateForm.isChkPassword()) {
				newUser.setPassword(originalUser.getPassword());
			}
			if(!userUpdateForm.isChkStation()) {
				newUser.setStation(originalUser.getStation());
			}
			userRepository.saveAndFlush(newUser);
			return "redirect:/userList";
		} else {
			return "userUpdateForm";
		}
	}
	

}
