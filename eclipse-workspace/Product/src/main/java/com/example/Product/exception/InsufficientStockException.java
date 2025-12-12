package com.example.Product.exception;

public class InsufficientStockException extends RuntimeException{
	public InsufficientStockException(String message) { super(message); }
}
