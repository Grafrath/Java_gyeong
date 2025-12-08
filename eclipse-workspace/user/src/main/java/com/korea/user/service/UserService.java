package com.korea.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.korea.user.model.UserEntity;
import com.korea.user.persistence.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	
	private final UserRepository repository;
	
	private void validate (UserEntity entity) {
		if (entity == null) {
			log.warn("유저 정보가 전달되지 않았습니다.");
			throw new RuntimeException("유저 정보가 전달되지 않았습니다.");
		}
	}
	
	public List<UserEntity> create (UserEntity entity) {
		validate(entity);
		
		repository.save(entity);
		
    	return repository.findAll();
    }
	
	public List<UserEntity> retrieve () {
		return repository.findAll();
	}
	
	public UserEntity retrieveOne (String email) {
		return repository.findByEmail(email);
	}
	
	@Transactional
	public UserEntity update (UserEntity entity) {
		validate(entity);
		
		Optional<UserEntity> original = repository.findById(entity.getId());
		
		original.ifPresent(user -> {
			user.setName(entity.getName());
			user.setEmail(entity.getEmail());
			
			repository.save(user);
		});
		
		return retrieveOne(entity.getEmail());
	}
	
	public boolean delete(int id) {
		Optional<UserEntity> user = repository.findById(id);
		
		if (user.isPresent()) {
			repository.delete(user.get());
			return true;
		} else {
			return false;
		}
	}
	
}