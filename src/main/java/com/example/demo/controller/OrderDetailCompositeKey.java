package com.example.demo.controller;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.Data;

@Embeddable
@Table(name = "t_orderdetail")
@Data
public class OrderDetailCompositeKey {
	
	@Column(name = "orderid")
	private Integer orderid;

	@Column(name = "pid")
	private String pid;

}