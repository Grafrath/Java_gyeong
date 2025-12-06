package com.example.Home.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Home.model.ProductEntity;
import com.example.Home.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository repository;
	
	private void validate (ProductEntity entity) {
		if (entity == null) {
			log.warn("상품 정보가 전달되지 않았습니다.");
			throw new RuntimeException("상품 정보가 전달되지 않았습니다.");
		}
		
		if (entity.getName() == null || entity.getName().trim().isEmpty()) {
			log.warn("상품 이름이 비어있습니다.");
			throw new RuntimeException("상품 이름이 비어있습니다.");
		}
		
		if (entity.getPrice() < 0) {
			log.warn("가격이 잘못되었습니다.");
			throw new RuntimeException("가격이 잘못되었습니다.");
		}
		
		if (entity.getStock() < 0) {
			log.warn("수량이 잘못되었습니다.");
			throw new RuntimeException("수량이 잘못되었습니다.");
		}
	}
	
	public ProductEntity create (ProductEntity entity) {
		validate(entity);
		
		ProductEntity saved = repository.save(entity);
		
		log.info("Entity Id : {} is saved", saved.getId());
		
		return saved;
	}
	
}