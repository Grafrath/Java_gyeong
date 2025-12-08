package com.korea.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
}

/*

com.korea.user.service
UserService

사용자를 생성하는 create메서드 생성하기
- 추가 후 모든 사용자 반환하기


com.korea.user.controller
UserController

유저를 추가하는 createUser메서드
HTTP 메서드 : POST

모든 사용자 조회
UserService
getAllUsers() 메서드 만들기

UserController
메서드명
getAllUsers()
HTTP메서드 : GET

이메일을 통한 사용자 검색
UserService
getUserByEmail() 메서드 만들기

UserController
HTTP메서드 : GET
메서드명 : getUserByEmail
리소스 : /{email}

ID를 통해 이름과 이메일 수정하기
UserService
getUserByEmail() 메서드 생성하기

UserController
http메서드 : put
메서드명 : updateUser

삭제하기
ID를 가지고 유저를 삭제하는 기능 만들기
UserService
메서드명 deleteUser
사용자가 존재하는지 확인
삭제 되면 true, 삭제 안되면 false를 반환
UserController
HTTP메서드 : DELETE
메서드명 : deleteUser
리소스 : /{id}
삭제에 성공하면 "User deleted successfully"
실패하면 .status(404).body("user not found with id " + id)

*/