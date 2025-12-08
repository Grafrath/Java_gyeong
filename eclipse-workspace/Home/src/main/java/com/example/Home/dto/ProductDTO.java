package com.example.Home.dto;

import com.example.Home.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {
	private long id;
	private String name;
	private int stock;
	private String description;
	private int price;
	
	public ProductDTO (ProductEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.stock = entity.getStock();
		this.description = 	entity.getDescription();
		this.price = entity.getPrice();
	}
	
	public static ProductEntity toEntity(ProductDTO dto) {
		return ProductEntity.builder()
				.id(dto.getId())
				.name(dto.getName())
				.stock(dto.getStock())
				.description(dto.getDescription())
				.price(dto.getPrice())
				.build();
	}
}
