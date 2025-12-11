package com.example.Product.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  id;
	private String name;
	private int price;
	private int stock;
	
	@CreationTimestamp
	private LocalDateTime createTime;
	@UpdateTimestamp
	private LocalDateTime updateTime;
}
/*
서비스계층 만들기
Service 만들기
com.korea.product.service 패키지 생성하기
ProductService 클래스 생성하기
ProductRepository 주입하기

표현계층 만들기
Controller 만들기
com.korea.product.controller 패키지 생성하기
ProductController 클래스 생성하기
ProductService 주입하기
*/