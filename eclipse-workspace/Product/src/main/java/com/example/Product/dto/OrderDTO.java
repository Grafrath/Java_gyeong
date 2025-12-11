package com.example.Product.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
	private int orderId;
	private int productId;
	private int count;
	private int totalprice;
	private LocalDateTime orderdate;
	
	public static List<OrderDTO> toListOrderDTO (List<Object[]> list) {
		return null;
		//List<OrderDTO> relist = list
		
	}

}
