package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.OrderDetailCompositeKey;
import com.example.demo.entity.OrderDetailPname;

public interface OrderDetailPnameRepository
		extends JpaRepository<OrderDetailPname, OrderDetailCompositeKey> {

	@Query(value = "SELECT orderid, pid, pname, amount FROM t_orderdetail"
			+ " JOIN t_order using(orderid) WHERE orderid=:orderid", nativeQuery = true)
	List<OrderDetailPname> findAllByOrderId(@Param("orderid") Integer orderid);

}