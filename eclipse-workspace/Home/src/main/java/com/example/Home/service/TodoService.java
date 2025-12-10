package com.example.Home.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
		
		if(!StringUtils.hasText(entity.getTitle())) {
			log.warn("Entity Title cannot be empty.");
			throw new RuntimeException("Entity Title cannot be empty.");
		}
	}
	
	public List<TodoEntity> create (TodoEntity entity) {		
		validate(entity);
		
		repository.save(entity);
		
		log.info("Entity Id : {} is saved", entity.getId());
		
		return getAll();
	}
	
	public List<TodoEntity> getAll() {
		return repository.findAllByOrderByDoneAscItemOrderAscIdDesc();
	}
	
	public Optional<TodoEntity> getOne(int id) {
		return repository.findById(id);
	}
	
	public List<TodoEntity> update(TodoEntity entity) {
		validate(entity);
		
		if (entity.getId() == 0) {
			log.warn("Entity ID must be provided for update.");
			throw new RuntimeException("Entity ID must be provided for update.");
		}
		
		TodoEntity original = repository.findById(entity.getId())
				.orElseThrow(() -> {
					log.warn("Entity not found for update: ID {}", entity.getId());
					return new RuntimeException("해당 ID의 Todo를 찾을 수 없습니다.");
				});
		
		original.setTitle(entity.getTitle());
		original.setDone(entity.isDone());
		repository.save(original);
		
		return getAll();
	}
	
	public List<TodoEntity> delete(TodoEntity entity) {
		if (entity == null || entity.getId() == 0) {
			log.warn("Entity ID must be provided for deletion.");
			throw new RuntimeException("삭제를 위한 ID는 필수입니다.");
		}
		
		try {
			repository.deleteById(entity.getId());
	        log.info("Entity ID: {} is deleted.", entity.getId());
		} catch (EmptyResultDataAccessException e) {
			log.warn("Entity not found for deletion: ID {}", entity.getId());
			throw new RuntimeException("해당 ID의 Todo를 찾을 수 없거나 이미 삭제되었습니다.");
		} catch (Exception e) {
			log.error("error deleting entity {}", entity.getId(), e);
			throw new RuntimeException("error deleting entity "+entity.getId());
		}
		
		return getAll();
	}
	
	public List<TodoEntity> updateOrder(List<TodoEntity> orderedTodos) {
		repository.saveAll(orderedTodos);
		
		return getAll();
	}
	
}
