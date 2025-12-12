package com.example.Product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Product.dto.OrderRequestDTO;
import com.example.Product.dto.OrderResponseDTO;
import com.example.Product.dto.ResponseDTO;
import com.example.Product.exception.InvalidInputException;
import com.example.Product.model.OrderEntity;
import com.example.Product.service.OrderService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService service;
	
	private void validateOrderRequest(OrderRequestDTO dto) {
		if (dto.getQuantity() <= 0) {
			throw new InvalidInputException("주문 수량은 1개 이상이어야 합니다.");
		}
	}
	
	// OrderResponseDTO 리스트를 ResponseDTO로 변환
	private ResponseEntity<ResponseDTO<OrderResponseDTO>> toResponseDTO(List<OrderResponseDTO> dto) {
		ResponseDTO<OrderResponseDTO> response = ResponseDTO
				.<OrderResponseDTO>builder().data(dto).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	// 단일 OrderEntity를 ResponseDTO로 변환
	private ResponseEntity<ResponseDTO<OrderResponseDTO>> toResponseDTO(OrderEntity entity) {
		OrderResponseDTO dto = new OrderResponseDTO(entity);
		ResponseDTO<OrderResponseDTO> response = ResponseDTO
				.<OrderResponseDTO>builder().item(dto).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/listAll")
	public ResponseEntity<?> listAllOrders() {
		List<OrderResponseDTO> dto = service.listAllOrders();
		return toResponseDTO(dto);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO dto) {
		OrderEntity savedEntity = service.createOrder(dto);
		return toResponseDTO(savedEntity);
	}
	
}