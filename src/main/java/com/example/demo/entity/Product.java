package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "t_product")
@Data
public class Product {
	
	@Id
	@Column(name = "pid")
	private String pid;
	
	@Column(name = "pname")
	private String pname;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "stock")
	private Integer stock;

}
