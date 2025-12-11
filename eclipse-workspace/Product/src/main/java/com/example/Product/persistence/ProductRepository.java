package com.example.Product.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Product.model.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	List<ProductEntity> findByName(String name);
	List<ProductEntity> findByNameOrderByIdDesc(String name);
}
