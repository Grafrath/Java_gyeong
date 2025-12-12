package com.example.Product.dto;

import java.time.LocalDateTime;

import com.example.Product.model.ProductEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter // 데이터를 읽기만 가능 (불변성)
public class ProductResponseDTO {
	private int id;
	private String name;
	private int price;
	private int stock;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	
	public ProductResponseDTO (ProductEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.stock = entity.getStock();
		this.price = entity.getPrice();
		this.createTime = entity.getCreateTime();
		this.updateTime = entity.getUpdateTime();
	}
}