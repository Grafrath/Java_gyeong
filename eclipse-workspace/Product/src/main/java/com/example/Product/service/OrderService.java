package com.example.Product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Product.dto.OrderRequestDTO;
import com.example.Product.dto.OrderResponseDTO;
import com.example.Product.exception.ProductNotFoundException;
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
	
	private final ProductService productService;
	private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    
    // 상품 조회
    private ProductEntity findProductForOrder(int productId) {
    	return productRepository.findById(productId).orElseThrow(
    			() -> new ProductNotFoundException("주문할 상품을 찾을 수 없습니다. (ID: " + productId + ")"));
    }
    
    // 주문 목록 조회
    public List<OrderResponseDTO> listAllOrders() {
    	List<OrderEntity> entities = orderRepository.findAllWithProductOrderByOrderTimeDesc();
    	
    	return entities.stream()
    			.map(OrderResponseDTO::new)
    			.collect(Collectors.toList());
    }
    
    @Transactional
    public OrderEntity createOrder(OrderRequestDTO requestDTO) {
    	int productId = requestDTO.getProductId();
    	int quantity = requestDTO.getQuantity();
    	
    	ProductEntity product = findProductForOrder(productId);
    	productService.decreaseStock(productId ,quantity);
    	
    	int orderPrice = product.getPrice();
    	int orderTotalPrice = orderPrice * quantity;
    	
    	OrderEntity order = OrderEntity.builder()
    			.product(product).quantity(quantity)
    			.orderPrice(orderPrice)
    			.orderTotalPrices(orderTotalPrice).build();
    	
    	return orderRepository.save(order);
    	}
    
}