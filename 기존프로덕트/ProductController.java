package com.example.Home.controller;

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

import com.example.Home.dto.ProductDTO;
import com.example.Home.dto.ResponseDTO;
import com.example.Home.model.ProductEntity;
import com.example.Home.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
    
	@PostMapping("/createProduct")
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto) {		
		try {
			ProductEntity entity = ProductDTO.toEntity(dto);
			ProductEntity saved = productService.create(entity);
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
	
	@GetMapping("/listProduct")
	public ResponseEntity<?> retrieveProduct() {
		try {
			List<ProductEntity> list = productService.retrieve();
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
	
	@GetMapping("/listProduct/{id}")
	public ResponseEntity<?> retrieveOne(@PathVariable("id") long id) {
		try {
			ProductEntity entity = productService.retrieveOne(id);
			ProductDTO dto = new ProductDTO(entity);
			
			return ResponseEntity.ok().body(dto);
		} catch (Exception e) {
			ResponseDTO<ProductDTO> response =
					ResponseDTO.<ProductDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO dto){
		try {
			ProductEntity entity = ProductDTO.toEntity(dto);
			ProductEntity updatedList = productService.update(entity);
			List<ProductDTO> dtos = List.of(new ProductDTO(updatedList));
			
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
	
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
		try {
			List<ProductEntity> entities = productService.delete(id);
			List<ProductDTO> dtos = entities.stream().map(ProductDTO::new).collect(Collectors.toList());
			ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<ProductDTO> response =
					ResponseDTO.<ProductDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}