package com.example.Home.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Home.model.TodoEntity;
import com.example.Home.persistence.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {
	
	private final TodoRepository repository;
	
	public String test() {
		return "Test Service";
	}
	
	public String testService () {
		TodoEntity entity = TodoEntity.builder().title("First").done(true).build();
		if (entity.isDone()) {
			return "Test Service with DB Access";
		}
		return "Test Service with DB Access fail";
	}
	
	private void validate (final TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null");
		}
		
		if(entity.getTitle() != null && entity.getTitle().trim().isEmpty()) {
			log.warn("Entity Title cannot be empty.");
			throw new RuntimeException("Entity Title cannot be empty.");
		}
		
		if(entity.getId() == 0) {
			log.warn("Entity ID must be set for update or delete operations.");
			throw new RuntimeException("Entity ID must be set.");
		}
	}
	
	public List<TodoEntity> create (TodoEntity entity) {		
		validate(entity);
		
		repository.save(entity);
		
		log.info("Entity Id : {} is saved by User Id: {}", entity.getId());
		
		return retrieve();
	}
	
	public List<TodoEntity> retrieve() {
		return repository.findAll();
	}
	
	public List<TodoEntity> update(TodoEntity entity) {
		validate(entity);
		
		Optional<TodoEntity> original = repository.findById(entity.getId());
		
		original.ifPresent(todo -> {
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			repository.save(todo);
		});
		
		return retrieve();
	}
	
	public List<TodoEntity> delete(TodoEntity entity) {
		validate(entity);
		
		try {
			Optional<TodoEntity> original = repository.findById(entity.getId());
			
			original.ifPresent(todo -> {
				repository.delete(todo);
			});
		} catch (Exception e) {
			log.error("error deleting entity {}", entity.getId(), e);
			throw new RuntimeException("error deleting entity "+entity.getId());
		}
		
		return retrieve();
	}	
}
