package com.example.Home.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Home.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
	@Query("SELECT t FROM TodoEntity t where t.userId=?1")
	List<TodoEntity> findByUserIdQuery(String userId);
}