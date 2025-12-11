package com.example.Product.dto;

import java.time.LocalDateTime;

import com.example.Product.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
	private int  id;
	private String name;
	private int price;
	private int stock;
	private LocalDateTime createTimeStamp;
	private LocalDateTime updateTimeStamp;
	
	public ProductDTO (ProductEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.stock = entity.getStock();
		this.price = entity.getPrice();
		this.createTimeStamp = entity.getCreateTime();
		this.updateTimeStamp = entity.getUpdateTime();
	}
	
	public static ProductEntity toEntity(ProductDTO dto) {
		return ProductEntity.builder()
				.id(dto.getId())
				.name(dto.getName())
				.stock(dto.getStock())
				.price(dto.getPrice())
				.build();
	}
}