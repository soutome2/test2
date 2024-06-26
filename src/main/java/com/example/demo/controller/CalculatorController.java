package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class CalculatorController {
 @GetMapping("/")
 public String index() {
 return "index";
 }
 @PostMapping("/add")
 public String add(@RequestParam("number1") Integer number1, 
 @RequestParam("number2") Integer number2, Model model) {
 Integer result = number1 + number2;
 model.addAttribute("result", result);
 return "index";
 }
}
