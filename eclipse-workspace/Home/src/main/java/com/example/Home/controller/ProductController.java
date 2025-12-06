package com.example.Home.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Home.dto.ProductDTO;
import com.example.Home.model.ProductEntity;
import com.example.Home.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
    
	@PostMapping("/Post")
	public ProductDTO createProduct(@RequestBody ProductDTO dto) {
		// DTO → Entity 변환
		ProductEntity entity = ProductDTO.toEntity(dto);
		
		// Service 호출(저장)
		ProductEntity savedEntity = (ProductEntity) productService.create(entity);
		
		// Entity → DTO 변환 후 반환
		return new ProductDTO(savedEntity);
    }
}