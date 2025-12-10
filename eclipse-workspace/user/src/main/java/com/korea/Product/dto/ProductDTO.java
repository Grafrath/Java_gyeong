package com.korea.Product.dto;

import java.time.LocalDateTime;

import com.korea.Product.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
	private long  id;
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
		this.createTimeStamp = entity.getCreateTimeStamp();
		this.updateTimeStamp = entity.getUpdateTimeStamp();
	}
	
	public static ProductEntity toEntity(ProductDTO dto) {
		return ProductEntity.builder()
				.name(dto.getName())
				.stock(dto.getStock())
				.price(dto.getPrice())
				.build();
	}
}