package com.example.Product.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false) // Order 테이블의 외래 키 컬럼명
	private ProductEntity product;
	
	private int quantity;
	
	// 주문 시점의 상품가 (OrderService에서 ProductEntity.getPrice()를 조회하여 저장)
	private int orderPrice;
	// 주문 총액 (OrderService에서 quantity * orderPrice로 계산하여 저장)
	private int orderTotalPrices;
	
	@CreationTimestamp
	private LocalDateTime orderTime;
}