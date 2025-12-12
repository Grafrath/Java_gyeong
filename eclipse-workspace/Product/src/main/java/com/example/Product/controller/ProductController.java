package com.example.Product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
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

import com.example.Product.dto.ProductRequestDTO;
import com.example.Product.dto.ProductResponseDTO;
import com.example.Product.dto.ResponseDTO;
import com.example.Product.exception.InvalidInputException;
import com.example.Product.model.ProductEntity;
import com.example.Product.service.ProductService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService service;
	
	private void validateProductRequest(ProductRequestDTO dto) {
		if (dto.getName() == null || dto.getName().trim().isEmpty()) {
			throw new InvalidInputException("상품 이름은 필수 항목이며 공백일 수 없습니다.");
		}
		if (dto.getPrice() < 0) {
			throw new InvalidInputException("가격은 0 이상이어야 합니다.");
		}
		if (dto.getStock() < 0) {
			throw new InvalidInputException("재고는 0개 이상이어야 합니다.");
		}
	}
	
	//엔티티 리스트를 DTO 리스트로 변환하여 ResponseDTO에 담아 반환
	private ResponseEntity<ResponseDTO<ProductResponseDTO>> toResponseDTO (List<ProductEntity> entities) {
		List<ProductResponseDTO> dtos = entities.stream()
				.map(ProductResponseDTO::new)
				.collect(Collectors.toList());
		
		ResponseDTO<ProductResponseDTO> response = ResponseDTO
				.<ProductResponseDTO>builder().data(dtos).build();
    	return ResponseEntity.ok().body(response);
	}
    
    //단일 엔티티를 DTO로 변환하여 ResponseDTO의 item 필드에 담아 반환
    private ResponseEntity<ResponseDTO<ProductResponseDTO>> toResponseDTO (ProductEntity entity) {
    	ProductResponseDTO dto = new ProductResponseDTO(entity);
    	ResponseDTO<ProductResponseDTO> response = ResponseDTO
    			.<ProductResponseDTO>builder().item(dto) .build();
    	
    	return ResponseEntity.ok().body(response);
    }
	
	@GetMapping("/listAll")
	public ResponseEntity<?> listAll() {
		List<ProductEntity> list = service.listAll();
		return toResponseDTO(list);
	}
	
	@GetMapping("/listId/{id}")
	public ResponseEntity<?> listId(@PathVariable("id") int id) {
		ProductEntity entity = service.listOne(id);
		return toResponseDTO(entity);
	}
	
	@GetMapping("listName")
	public ResponseEntity<?> listName(@RequestParam("name") String name) {
		List<ProductEntity> entities = service.listByName(name);
		return toResponseDTO(entities);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody ProductRequestDTO dto) {
		validateProductRequest(dto);
		
		ProductEntity entity = dto.toEntity();
		ProductEntity saved = service.create(entity);
		
		return toResponseDTO(saved);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductRequestDTO dto) {
		validateProductRequest(dto);
		
		ProductEntity entity = dto.toEntity();
		ProductEntity updated = service.update(entity);
		
		return toResponseDTO(updated);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}