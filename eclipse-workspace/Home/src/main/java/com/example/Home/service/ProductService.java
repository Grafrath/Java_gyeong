package com.example.Home.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Home.model.ProductEntity;
import com.example.Home.persistence.ProductRepository;

import jakarta.transaction.Transactional;
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
	
	//전체 조회
	public List<ProductEntity> retrieve () {
		return repository.findAll();
	}
	
	//단일 조회
    public ProductEntity retrieveOne(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
    }
	
    //생성
    public ProductEntity create (ProductEntity entity) {
    	validate(entity);
		
    	return repository.save(entity);
    }
	
	//수정
	@Transactional
	public ProductEntity update(ProductEntity entity) {
		validate(entity);
		
		ProductEntity product = repository.findById(entity.getId())
				.orElseThrow(() -> new RuntimeException("상품이 존재하지 않습니다."));
		
		product.setName(entity.getName());
		product.setPrice(entity.getPrice());
		product.setStock(entity.getStock());
		product.setDescription(entity.getDescription());
		
		return repository.save(product);
	}
	
	//삭제
	public List<ProductEntity> delete(long id) {
		Optional<ProductEntity> product = repository.findById(id);
		
		if (product.isEmpty()) {
			throw new RuntimeException("해당 상품이 존재하지 않습니다.");
		}
		
		repository.deleteById(id);
		return repository.findAll();
	}
	
}