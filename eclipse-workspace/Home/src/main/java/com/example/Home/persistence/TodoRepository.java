package com.example.Home.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Home.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer>{
	List<TodoEntity> findAllByOrderByDoneAscItemOrderAscIdDesc();
}