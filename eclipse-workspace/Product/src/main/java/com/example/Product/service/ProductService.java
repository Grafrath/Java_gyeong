package com.example.Product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Product.model.ProductEntity;
import com.example.Product.persistence.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository repository;
	
	//전체 조회
	@Transactional
	public List<ProductEntity> listAll () {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
	}
	
	//ID로 단일 조회 
	@Transactional
	public ProductEntity listOne(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
	}
	
	//상품명으로 단일 조회
	@Transactional
	public List<ProductEntity> listByName(String name) {
		List<ProductEntity> products = repository.findByNameOrderByIdDesc(name);
		
		if (products.isEmpty()) {
			throw new RuntimeException("상품을 찾을 수 없습니다.");
		}
		
		return products;
	}
	
	@Transactional
	public ProductEntity create (ProductEntity entity) {
		
    	return repository.save(entity);
    }
	
	@Transactional
	public ProductEntity update(ProductEntity entity) {
		
		ProductEntity original = repository.findById(entity.getId())
				.orElseThrow(() -> new RuntimeException("수정할 상품을 찾을 수 없습니다."));
		
		original.setName(entity.getName());
		original.setPrice(entity.getPrice());
		original.setStock(entity.getStock());
		
		return original;
	}
	
	@Transactional
	public List<ProductEntity> delete(Integer id) {
		Optional<ProductEntity> product = repository.findById(id);
		
		if (product.isEmpty()) {
			throw new RuntimeException("해당 상품이 존재하지 않습니다.");
		}
		
		repository.deleteById(id);
		return repository.findAll();
	}
	
	@Transactional
	public boolean decreaseStock(int productId, int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("주문 수량은 1개 이상이어야 합니다.");
		}
		
		int updatedRows = repository.decreaseStock(productId, quantity);
		
		if (updatedRows == 0) {
			log.warn("재고 차감 실패: Product ID: {}, Requested Quantity: {}", productId, quantity);
			
			Optional<ProductEntity> product = repository.findById(productId);
			
			if (product.isEmpty()) {
				throw new RuntimeException("주문하려는 상품을 찾을 수 없습니다. (ID: " + productId + ")");
			} else {
				throw new RuntimeException("재고가 부족하여 주문할 수 없습니다. 현재 재고: " + product.get().getStock());
			}
		}
		
		return true;
	}
	
}