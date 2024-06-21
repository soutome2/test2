package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.entity.Product;
import com.example.demo.form.ProductUpdateForm;
import com.example.demo.repository.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	public Product getByPid(ProductUpdateForm productUpdateForm, BindingResult result) {
		
		Optional<Product> optionalProduct=productRepository.findById(productUpdateForm.getPid());
		
		if(optionalProduct.isEmpty()) {
			result.addError(new FieldError(
					result.getObjectName(),"pid","存在しない商品番号です。"
					));
			return null;
		}
		return optionalProduct.get();
	}

}
