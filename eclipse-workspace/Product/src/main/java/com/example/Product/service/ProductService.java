package com.example.Product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Product.exception.InsufficientStockException;
import com.example.Product.exception.ProductNotFoundException;
import com.example.Product.model.ProductEntity;
import com.example.Product.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository repository;
	
	private ProductEntity getExistingProduct(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(
						"상품을 찾을 수 없습니다. (ID: " + id + ")"));
	}
	
	//전체 조회
	@Transactional(readOnly = true)
	public List<ProductEntity> listAll () {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
	}
	
	//ID로 단일 조회 
	@Transactional(readOnly = true)
	public ProductEntity listOne(int id) {
		return getExistingProduct(id);
	}
	
	//상품명으로 단일 조회
	@Transactional(readOnly = true)
	public List<ProductEntity> listByName(String name) {
		List<ProductEntity> products = repository.findByNameOrderByIdDesc(name);
		
		if (products.isEmpty()) {
			throw new ProductNotFoundException("상품을 찾을 수 없습니다. (Name: " + name + ")");
		}
		
		return products;
	}
	
	@Transactional
	public ProductEntity create (ProductEntity entity) {
    	return repository.save(entity);
    }
	
	@Transactional
	public ProductEntity update(ProductEntity entity) {
		ProductEntity original = getExistingProduct(entity.getId());
		
		original.setName(entity.getName());
		original.setPrice(entity.getPrice());
		original.setStock(entity.getStock());
		
		return original;
	}
	
	@Transactional
	public void delete(Integer id) {
		if (!repository.existsById(id)) {
			throw new ProductNotFoundException("해당 상품이 존재하지 않습니다. (ID: " + id + ")");
		}
		
		repository.deleteById(id);
	}
	
	@Transactional
	public boolean decreaseStock(int productId, int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("주문 수량은 1개 이상이어야 합니다.");
		}
		
		int updatedRows = repository.decreaseStock(productId, quantity);
		
		if (updatedRows == 0) {
			log.warn("재고 차감 실패: Product ID: {}, Requested Quantity: {}", productId, quantity);
			
			try {
				ProductEntity product = getExistingProduct(productId);
				
				throw new InsufficientStockException(
						"재고가 부족하여 주문할 수 없습니다. 현재 재고: " + product.getStock());
			} catch (ProductNotFoundException e) {
				throw e;
			}
		}
		
		return true;
	}
	
}