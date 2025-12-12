package com.korea.productApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.korea.productApp.entity.ProductEntity;
import com.korea.productApp.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepository repository;
	
	//리스트 반환
	public List<ProductEntity> findProduct () {
		return repository.findAll();
	}
	
	//Id 통해서 한건 조회 - 없으면 예외 발생시키기
	public ProductEntity findProductById(int id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
	}
	
	//저장 후 전체 조회후 반환
	public List<ProductEntity> addProduct (ProductEntity entity) {
		repository.save(entity);
    	return repository.findAll();
    }
	
	//수정하기  findById()를 통해 한건 반환, 데이터 있으면 name/price/stock 수정
	public ProductEntity modifyProduct(ProductEntity entity) {
		
		ProductEntity original = repository.findById(entity.getId())
				.orElseThrow(() -> new RuntimeException("수정할 상품을 찾을 수 없습니다."));
		
		original.setName(entity.getName());
		original.setPrice(entity.getPrice());
		original.setStock(entity.getStock());
		
		return original;
	}
	
	//삭제하기 findById()를 통해 한 건 반환, 없으면 예외
	public List<ProductEntity> deletProduct(Integer id) {
		Optional<ProductEntity> product = repository.findById(id);
		
		if (product.isEmpty()) {
			throw new RuntimeException("해당 상품이 존재하지 않습니다.");
		}
		
		repository.deleteById(id);
		return repository.findAll();
	}

}
