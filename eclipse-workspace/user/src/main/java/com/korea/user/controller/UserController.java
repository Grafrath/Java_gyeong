package com.korea.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.user.dto.ResponseDTO;
import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@PostMapping("/create")
	public ResponseEntity<?> create (@RequestBody UserDTO dto) {		
		try {
			UserEntity entity = UserDTO.toEntity(dto);
			
			List<UserEntity> entities = service.create(entity);
			
			List<UserDTO> dtos = entities.stream().map(UserDTO::new).collect(Collectors.toList());
			ResponseDTO<UserDTO> response = ResponseDTO
					.<UserDTO>builder()
					.data(dtos)
					.build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<UserDTO>response =
					ResponseDTO.<UserDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers () {
		try {
			List<UserEntity> list = service.retrieve();
			List<UserDTO> dtos = new ArrayList<>();
			for (UserEntity user : list) {
				dtos.add(new UserDTO(user));
			}
			ResponseDTO<UserDTO> response = ResponseDTO
					.<UserDTO>builder()
					.data(dtos)
					.build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<UserDTO> response =
					ResponseDTO.<UserDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> getUserbyEmail (@PathVariable("email") String email) {
		try {
			UserEntity entity = service.retrieveOne(email);
			UserDTO dto = new UserDTO(entity);
			
			return ResponseEntity.ok().body(dto);
		} catch (Exception e) {
			ResponseDTO<UserDTO> response =
					ResponseDTO.<UserDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser (@RequestBody UserDTO dto) {
		try {
			UserEntity entity = service.update(UserDTO.toEntity(dto));
			UserDTO dtos = new UserDTO(entity);
			List<UserDTO> list = new ArrayList<>();
			list.add(dtos);
			
			ResponseDTO<UserDTO> response = ResponseDTO
					.<UserDTO>builder()
					.data(list)
					.build();
					
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			ResponseDTO<UserDTO> response =
					ResponseDTO.<UserDTO>builder().error(e.getMessage()).build();
			
			return ResponseEntity.badRequest().body(response);
		}		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		boolean deleted = service.delete(id);
		
		if (deleted) {
			return ResponseEntity.ok("User deleted successfully");
		} else {
			return ResponseEntity.status(404).body("user not found with id " + id);
		}
	}
	
}