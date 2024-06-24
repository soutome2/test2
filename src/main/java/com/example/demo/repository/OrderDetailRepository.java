package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.OrderDetailCompositeKey;

public interface OrderDetailRepository
		extends JpaRepository<OrderDetail, OrderDetailCompositeKey> {
}