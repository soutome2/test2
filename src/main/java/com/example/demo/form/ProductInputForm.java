package com.example.demo.form;

import com.example.demo.entity.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductInputForm {
	
	@NotBlank(message = "商品番号を入力して下さい")
	private String pid;
	
	@NotBlank(message = "商品名を入力して下さい")
	private String pname;
	
	@NotBlank(message = "カテゴリを入力して下さい")
	private String category;
	
	@NotNull(message = "単価を入力して下さい")
	private Integer price;
	
	@NotNull(message = "在庫を入力して下さい")
	private Integer stock;
	
	public Product getEntity() {
		Product product = new Product();
		product.setPid(pid);
		product.setPname(pname);
		product.setCategory(category);
		product.setPrice(price);
		product.setStock(stock);
		return product;
	}

}
