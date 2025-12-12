package com.example.Product.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Product.model.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	Optional<ProductEntity> findByName(String name);
	List<ProductEntity> findByNameOrderByIdDesc(String name);
	
	@Modifying
    @Query("UPDATE ProductEntity p SET p.stock = p.stock - :quantity, p.updateTime = CURRENT_TIMESTAMP " +
           "WHERE p.id = :productId AND p.stock >= :quantity")
    int decreaseStock(@Param("productId") int productId, @Param("quantity") int quantity);
}