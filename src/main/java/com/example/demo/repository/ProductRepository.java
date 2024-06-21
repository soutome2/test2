package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	
	public List<Product> findAllByOrderByPid();
	public List<Product> findAllByOrderByPriceDescPid();
	public List<Product> findByPriceAndStock(Integer price, Integer stock);
	public List<Product> findByPnameLike(String key);
	public List<Product> findByPnameLikeOrderByPrice(String key);

}
