package com.example.Product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Product.dto.ProductDTO;
import com.example.Product.dto.ResponseDTO;
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
	public List<ProductEntity> listAll () {
		return repository.findAll();
	}
	
	//ID로 단일 조회 
	public ProductEntity listOne(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
	}
	
	//상품명으로 단일 조회
	public List<ProductEntity> listByName(String name) {
		List<ProductEntity> products = repository.findByName(name);
		
		if (products.isEmpty()) {
			throw new RuntimeException("상품을 찾을 수 없습니다.");
		}
		
		return products;
	}
	
	public ProductEntity create (ProductEntity entity) {
    	validate(entity);
    	repository.save(entity);
		
    	return repository.save(entity);
    }
	
	@Transactional
	public ProductEntity update(ProductEntity entity) {
		validate(entity);
		
		ProductEntity original = repository.findById(entity.getId())
				.orElseThrow(() -> new RuntimeException("수정할 상품을 찾을 수 없습니다."));
		
		original.setName(entity.getName());
		original.setPrice(entity.getPrice());
		original.setStock(entity.getStock());
		
		return original;
	}
	
	public List<ProductEntity> delete(Integer id) {
		Optional<ProductEntity> product = repository.findById(id);
		
		if (product.isEmpty()) {
			throw new RuntimeException("해당 상품이 존재하지 않습니다.");
		}
		
		repository.deleteById(id);
		return repository.findAll();
	}
	
	public ResponseDTO<ProductDTO> resopon (List<ProductEntity> entity) {
		List<ProductDTO> dto = entity.stream()
				.map(ProductDTO::new)
				.collect(Collectors.toList());
		ResponseDTO<ProductDTO> response = ResponseDTO
				.<ProductDTO>builder()
				.data(dto)
				.build();
		
		return response;
	}
	
}
