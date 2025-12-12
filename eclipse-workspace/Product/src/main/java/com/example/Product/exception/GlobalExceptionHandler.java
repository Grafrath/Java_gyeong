package com.example.Product.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.Product.dto.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ResponseDTO<?>> handleProductNotFoundException(ProductNotFoundException e) {
		log.error("404 Not Found: {}", e.getMessage());
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ResponseDTO.builder().error(e.getMessage()).build());
	}
	
	@ExceptionHandler(InsufficientStockException.class)
	public ResponseEntity<ResponseDTO<?>> handleInsufficientStockException(InsufficientStockException e) {
		log.error("409 Conflict: {}", e.getMessage());
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(ResponseDTO.builder().error(e.getMessage()).build());
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ResponseDTO<?>> handleInvalidInputException(InvalidInputException e) {
		log.error("400 Bad Request (Input): {}", e.getMessage());
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(ResponseDTO.builder().error(e.getMessage()).build());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO<?>> handleValidationExceptions(MethodArgumentNotValidException e) {
		// DTO 필드별 오류 메시지를 추출하여 클라이언트에게 전달
		String errorMessage = e.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
				.collect(Collectors.joining(", "));
		
		log.error("400 Bad Request (Validation): {}", errorMessage);
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(ResponseDTO.builder().error("입력 데이터 유효성 검증 실패: " + errorMessage).build());
	}

}