package com.example.Product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Product.dto.OrderRequestDTO;
import com.example.Product.dto.OrderResponseDTO;
import com.example.Product.dto.ResponseDTO;
import com.example.Product.model.OrderEntity;
import com.example.Product.service.OrderService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService service;
	
	@GetMapping("/listAll")
	public ResponseEntity<?> listAllOrders() {
		try {
			List<OrderResponseDTO> dtos = service.listAllOrders();
			
			return toResponseDTO(dtos);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
	private ResponseEntity<ResponseDTO<?>> toResponseDTO(List<OrderResponseDTO> dtos) {
		
		ResponseDTO<OrderResponseDTO> response = ResponseDTO.<OrderResponseDTO>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}
	
	private ResponseEntity<ResponseDTO<?>> toResponseDTO(OrderEntity entity) {
		ResponseDTO<OrderResponseDTO> response = ResponseDTO
				.<OrderResponseDTO>builder()
				.data(List.of(new OrderResponseDTO(entity))).build();
		return ResponseEntity.ok().body(response);
	}
	
	private ResponseEntity<ResponseDTO<?>> errorResponse(Exception e) {
		ResponseDTO<OrderResponseDTO> response = ResponseDTO.<OrderResponseDTO>builder()
				.error(e.getMessage()).build();
		
		return ResponseEntity.badRequest().body(response);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO dto) {
		try {
			OrderEntity savedEntity = service.createOrder(dto);
			
			return toResponseDTO(savedEntity);
		} catch (Exception e) {
			return errorResponse(e);
		}
	}
	
}