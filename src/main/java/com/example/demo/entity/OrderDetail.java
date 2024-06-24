package com.example.demo.entity;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "t_orderdetail")
@IdClass(OrderDetailCompositeKey.class)
@Data
public class OrderDetail implements Persistable<OrderDetailCompositeKey>{

	@Id
	@Column(name = "orderid")
	private Integer orderid;

	@Id
	@Column(name = "pid")
	private String pid;

	@Column(name = "amount")
	private Integer amount;
	
	@Override
	public OrderDetailCompositeKey getId() {
		return null;
	}
	
	@Override
	public boolean isNew() {
		return true;
	}

}