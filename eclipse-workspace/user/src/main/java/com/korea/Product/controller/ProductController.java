package com.korea.Product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.Product.dto.ProductDTO;
import com.korea.Product.dto.ResponseDTO;
import com.korea.Product.model.ProductEntity;
import com.korea.Product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService service;
	
	@GetMapping("/listAll")
	public ResponseEntity<?> listAll() {
		try {
			List<ProductEntity> list = service.listAll();
			List<ProductDTO> dtos = list.stream().map(ProductDTO::new).collect(Collectors.toList());
			ResponseDTO<ProductDTO> response = ResponseDTO
					.<ProductDTO>builder()
					.data(dtos)
					.build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<ProductDTO> response =
					ResponseDTO.<ProductDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping("/listId/{id}")
	public ResponseEntity<?> listId(@PathVariable("id") long id) {
		try {
			ProductEntity entity = service.listOne(id);
			ProductDTO dto = new ProductDTO(entity);
			
			ResponseDTO<ProductDTO> response = ResponseDTO
					.<ProductDTO>builder()
					.data(List.of(dto))
					.build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<ProductDTO> response =
					ResponseDTO.<ProductDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping("/listName/{name}")
	public ResponseEntity<?> listName(@PathVariable("name") String name) {
		try {
			List<ProductEntity> entities = service.listByName(name);
			List<ProductDTO> dtos = entities.stream()
					.map(ProductDTO::new)
					.collect(Collectors.toList());
			
			ResponseDTO<ProductDTO> response = ResponseDTO
					.<ProductDTO>builder()
					.data(dtos)
					.build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<ProductDTO> response =
					ResponseDTO.<ProductDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto) {
		try {
			ProductEntity entity = ProductDTO.toEntity(dto);
			ProductEntity saved = service.create(entity);
			ResponseDTO<ProductDTO> response = ResponseDTO
					.<ProductDTO>builder()
					.data(List.of(new ProductDTO(saved)))
					.build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<ProductDTO>response =
					ResponseDTO.<ProductDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO dto) {
		try {
			ProductEntity entity = ProductDTO.toEntity(dto);
			ProductEntity updated = service.update(entity);
			
			ResponseDTO<ProductDTO> response = ResponseDTO
					.<ProductDTO>builder()
					.data(List.of(new ProductDTO(updated)))
					.build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<ProductDTO> response =
					ResponseDTO.<ProductDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
		try {
			List<ProductEntity> entities = service.delete(id);
			List<ProductDTO> dtos = entities.stream()
					.map(ProductDTO::new)
					.collect(Collectors.toList());
			
			ResponseDTO<ProductDTO> response = ResponseDTO
					.<ProductDTO>builder()
					.data(dtos)
					.build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<ProductDTO> response =
					ResponseDTO.<ProductDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}
