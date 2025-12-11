package com.example.Product.dto;

import java.time.LocalDateTime;

import com.example.Product.model.OrderEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderResponseDTO {
	private int orderId;
	private int productId;
	private String productName;
	private int quantity;
	private int orderTotalPrices;
	private LocalDateTime orderTime;
	
	public OrderResponseDTO (OrderEntity entity) {
		this.orderId = entity.getOrderId();
		this.productId = entity.getProduct().getId();
		this.productName = entity.getProduct().getName();
		this.quantity = entity.getQuantity();
		this.orderTotalPrices = entity.getOrderTotalPrices();
		this.orderTime = entity.getOrderTime();
	}
}
