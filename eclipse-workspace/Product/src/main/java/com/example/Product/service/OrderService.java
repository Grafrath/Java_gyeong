package com.example.Product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Product.dto.OrderRequestDTO;
import com.example.Product.model.OrderEntity;
import com.example.Product.model.ProductEntity;
import com.example.Product.persistence.OrderRepository;
import com.example.Product.persistence.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
	
	private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    
    private void validateOrder(OrderRequestDTO dto) {
    	if (dto.getQuantity() <= 0) {
    		log.warn("주문 수량은 0보다 커야 합니다.");
    		throw new RuntimeException("주문 수량은 0보다 커야 합니다.");
    	}
    }
    
    public List<OrderEntity> listAllOrders () {
    	return orderRepository.findAllByOrderByOrderTimeDesc();
    }
    
    public OrderEntity listOneOrder (int id) {
    	return orderRepository.findById(id)
    			.orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
    }
    
    @Transactional
    public OrderEntity createOrder(OrderRequestDTO orderDto) {
    	validateOrder(orderDto);
    	
    	ProductEntity product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(() -> new RuntimeException("상품 ID를 찾을 수 없습니다: " + orderDto.getProductId()));
		
    	return null;
    }
    
}
