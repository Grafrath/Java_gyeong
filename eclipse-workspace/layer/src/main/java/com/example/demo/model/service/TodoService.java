package com.example.demo.model.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.demo.model.persistence.TodoEntity;
import com.example.demo.model.persistence.TodoRepository;

@Service
@RequiredArgsConstructor
public class TodoService {
	
	private final TodoRepository repository;
	
	public String testService() {
		return "Test Service";
	}
	
	public String todoService() {
		TodoEntity entity = TodoEntity
				.builder()
				.title("My first todo item")
				.build();
		repository.save(entity);
		//indById()의 반환형 Optional
		TodoEntity savedEntity = repository.findById(entity.getId())
			.orElseThrow(() -> new RuntimeException("데이터 없음"));
		return savedEntity.getTitle();
	}

}
