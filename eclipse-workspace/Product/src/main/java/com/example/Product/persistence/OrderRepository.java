package com.example.Product.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Product.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
	
	@Query("SELECT o.orderId,"
			+ "o.product.name,"
			+ "o.quantity,"
			+ "o.product.price,"
			+ "(o.quantity * o.product.price) AS totalPrice FROM OrderEntity o")
	List<Object[]> findAllByOrderTotalPrices();
	
    List<OrderEntity> findAllByOrderByOrderTimeDesc();
	
}
