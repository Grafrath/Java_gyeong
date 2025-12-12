package com.korea.productApp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.productApp.dto.ProductDTO;
import com.korea.productApp.dto.ResponseDTO;
import com.korea.productApp.entity.ProductEntity;
import com.korea.productApp.service.ProductService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService service;
	
	@GetMapping("/products")
	public ResponseEntity<?> listAll() {		
		try {
			List<ProductEntity> list = service.findProduct();
			
			return toResponseDTO(list);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<?> listId(@PathVariable("id") int id) {
		try {
			ProductEntity entity = service.findProductById(id);
			
			return toResponseDTO(entity);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto) {		
		try {
			ProductEntity entity = ProductDTO.toEntity(dto);
			List<ProductEntity> saved = service.addProduct(entity);
			
			return toResponseDTO(saved);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable("id") int id) {
		try {
			ProductEntity entity = service.findProductById(id);
			ProductEntity updated = service.modifyProduct(entity);
			
			return toResponseDTO(updated);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {		
		try {
			List<ProductEntity> entities = service.deletProduct(id);
			
			return toResponseDTO(entities);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	//엔티티리스트를 DTO 리스트로 변환
		private ResponseEntity<ResponseDTO<?>> toResponseDTO(List<ProductEntity> entities) {
			List<ProductDTO> dtos = entities.stream()
					.map(ProductDTO::new)
					.collect(Collectors.toList());
			
			ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().data(dtos).build();
	    	return ResponseEntity.ok().body(response);
		}
	    
	    //단일 엔티티를 DTO 리스트로 변환
	    private ResponseEntity<ResponseDTO<?>> toResponseDTO(ProductEntity entity) {
	    	ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
	    			.data(List.of(new ProductDTO(entity)))
	    			.build();
	    	return ResponseEntity.ok().body(response);
	    }
	    
	    //에러처리
	    private ResponseEntity<ResponseDTO<?>> errorResponse(Exception e) {
	    	ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
	    			.error(e.getMessage())
	    			.build();
	    	return ResponseEntity.badRequest().body(response);
	    }

}
