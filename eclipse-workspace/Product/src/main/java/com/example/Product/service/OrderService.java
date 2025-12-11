package com.example.Product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Product.dto.OrderRequestDTO;
import com.example.Product.dto.OrderResponseDTO;
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
    
    private ProductEntity validateOrder(int productId, int quantity) {
    	if (quantity <= 0) {
    		log.warn("주문 수량 오류: 수량은 1개 이상이어야 합니다. Quantity: {}", quantity);
    		throw new RuntimeException("주문 수량은 1개 이상이어야 합니다.");
    	}
    	
    	Optional<ProductEntity> productOptional = productRepository.findById(productId);
    	
    	if (productOptional.isEmpty()) {
    		log.warn("상품 없음 오류: Product ID: {}", productId);
    		throw new RuntimeException("주문하려는 상품을 찾을 수 없습니다.");
    	}
    	
    	ProductEntity product = productOptional.get();
    	
    	if (product.getStock() < quantity) {
    		log.warn("재고 부족 오류: Product ID: {}, Requested: {}, Current Stock: {}",productId, quantity, product.getStock());
    		throw new RuntimeException("재고가 부족하여 주문할 수 없습니다. 현재 재고: " + product.getStock());
    	}
    	
    	return product;
    }
    
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
    	
    	ProductEntity product = validateOrder(productId, quantity);
    	
    	product.setStock(product.getStock() - quantity);
    	productRepository.save(product);
    	
    	int orderTotalPrice = product.getPrice() * quantity;
    	
    	OrderEntity order = OrderEntity.builder()
    			.product(product).quantity(quantity)
    			.orderTotalPrices(orderTotalPrice).build();
    	
    	return orderRepository.save(order);
    }
    
}
