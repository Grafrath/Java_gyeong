package com.example.Product.dto;

import com.example.Product.model.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequestDTO {
	private int id;
	private String name;
	private int price;
	private int stock;
	
	public ProductEntity toEntity() {
		return ProductEntity.builder()
				.id(this.id)
				.name(this.name)
				.stock(this.stock)
				.price(this.price)
				.build();
	}
}