package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.OrderDetailCompositeKey;
import com.example.demo.entity.UserOrderDetailPname;

public interface UserOrderDetailPnameRepository
		extends JpaRepository<UserOrderDetailPname, OrderDetailCompositeKey> {

	@Query(value = "SELECT eid, ename, orderid, pid, pname, amount FROM t_orderdetail"
			+ " JOIN t_order using(orderid)"
			+ " JOIN t_product using(pid) "
			+ " JOIN m_user using(eid) WHERE ename=:ename", nativeQuery = true)
	List<UserOrderDetailPname> findAllByEId(@Param("ename") String ename);

}