package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Product;
import com.example.demo.form.ProductInputForm;
import com.example.demo.form.ProductUpdateForm;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ProductController {
	
	private final ProductRepository productRepository;
	private final ProductService productService;
	
	@CrossOrigin
	@GetMapping("/productList")
	public ModelAndView showProductList(ModelAndView mv) {
		List<Product> productList = productRepository.findAllByOrderByPid();
		mv.addObject("productList", productList);
		mv.setViewName("productList");
		return mv;
	}
	@CrossOrigin
	@GetMapping("/productListfindByPriceAndStock")
	public ModelAndView showProductFindByStockAndPrice(@RequestParam("price") Integer price, @RequestParam("stock") Integer stock, ModelAndView mv) {
		List<Product> productList = productRepository.findByPriceAndStock(price, stock);
		mv.addObject("productList", productList);
		mv.setViewName("productList");
		return mv;
	}
	@CrossOrigin
	@GetMapping("/productListLike")
	public ModelAndView showProductListLike(ModelAndView mv) {
		List<Product> productList = productRepository.findByPnameLike("%ボールペン%");
		mv.addObject("productList", productList);
		mv.setViewName("productList");
		return mv;
	}
	@CrossOrigin
	@GetMapping("/productListLikeByPrice")
	public ModelAndView showProductListLikeByPrice(ModelAndView mv) {
		List<Product> productList = productRepository.findByPnameLikeOrderByPrice("%ボールペン%");
		mv.addObject("productList", productList);
		mv.setViewName("productList");
		return mv;
	}
	@CrossOrigin
	@GetMapping("/productInputForm")
	public String showForm(ProductInputForm productInputForm) {
		return "productInputForm";
	}
	@CrossOrigin
	@PostMapping("/productResist")
	public String regist(@ModelAttribute @Validated ProductInputForm productInputForm, BindingResult result) {
		if(!result.hasErrors()) {
			Product product = productInputForm.getEntity();
			productRepository.saveAndFlush(product);
			return "redirect:/productList";
		} else {
			return "productInputForm";
		}
	}
	@CrossOrigin
	@GetMapping("/productUpdateForm")
	public String showForm(ProductUpdateForm productUpdateForm) {
		return "productUpdateForm";
	}
	@CrossOrigin
	@PostMapping("/productUpdate")
	public String update(@ModelAttribute @Validated ProductUpdateForm productUpdateForm, BindingResult result) {
		Product originalProduct = productService.getByPid(productUpdateForm, result);
		
		if(!result.hasErrors()) {
			Product newProduct = productUpdateForm.getEntity();
			newProduct.setPname(originalProduct.getPname());
			newProduct.setCategory(originalProduct.getCategory());
			productRepository.saveAndFlush(newProduct);
			return "redirect:/productList";
		} else {
			return "productUpdateForm";
		}
	}

}
