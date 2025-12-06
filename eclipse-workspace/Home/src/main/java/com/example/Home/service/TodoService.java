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
		TodoEntity entity = TodoEntity.builder().title("First").build();
		repository.save(entity);
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return  savedEntity.getTitle();
	}
	
	private void validate (TodoEntity entity) {
		if(entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null");
		}
		
		if(entity.getUserId() == null) {
			log.warn("Unknown user");
			throw new RuntimeException("Unknown user");
		}
	}
	
	public List<TodoEntity> create (TodoEntity entity) {
		validate(entity);
		
		repository.save(entity);
		
		log.info("Entity Id : {} is saved",entity.getId());
		
		return repository.findByUserIdQuery(entity.getUserId());
	}
	
	public List<TodoEntity> retrieve(String userId) {
		return repository.findByUserIdQuery(userId);
	}
	
	public List<TodoEntity> update(TodoEntity entity) {
		//저장할 엔티티가 유효한지 확인한다.
		validate(entity);
		
		//넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다.
		//존재하지 않는 엔티티는 업데이트 할 수 없기 때문이다.
		Optional<TodoEntity> original = repository.findById(entity.getId());
		
		original.ifPresent(todo -> {
			//반환된 TodoEntity가 존재하면 값을 새 Entity값으로 덮어씌운다.
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			//데이터베이스에 새 값을 저장한다.
			repository.save(todo);
			});
		
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(TodoEntity entity) {
		validate(entity);
		try {
			repository.delete(entity);
		} catch (Exception e) {
			log.error("error deleting entity ",entity.getId(),e);
			throw new RuntimeException("error deleting entity "+entity.getId());
		}
		
		return retrieve(entity.getUserId());
	}
	
}
