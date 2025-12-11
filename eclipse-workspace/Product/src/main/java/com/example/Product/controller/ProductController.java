package com.example.Product.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Product.dto.ProductDTO;
import com.example.Product.dto.ResponseDTO;
import com.example.Product.model.ProductEntity;
import com.example.Product.service.ProductService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService service;
	
	@GetMapping("/listAll")
	public ResponseEntity<?> listAll() {
		try {
			List<ProductEntity> list = service.listAll();
			
			return toResponseDTO(list);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	@GetMapping("/listId/{id}")
	public ResponseEntity<?> listId(@PathVariable("id") int id) {
		try {
			ProductEntity entity = service.listOne(id);
			
			return toResponseDTO(entity);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	@GetMapping("listName")
	public ResponseEntity<?> listName(@RequestParam("name") String name) {
		try {
			List<ProductEntity> entities = service.listByName(name);
			
			return toResponseDTO(entities);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto) {
		try {
			ProductEntity entity = ProductDTO.toEntity(dto);
			ProductEntity saved = service.create(entity);
			
			return toResponseDTO(saved);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO dto) {
		try {
			ProductEntity entity = ProductDTO.toEntity(dto);
			ProductEntity updated = service.update(entity);
			
			return toResponseDTO(updated);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
		try {
			List<ProductEntity> entities = service.delete(id);
			
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