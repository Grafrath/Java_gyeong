package com.example.Home.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Home.dto.ResponseDTO;
import com.example.Home.dto.TodoDTO;
import com.example.Home.model.TodoEntity;
import com.example.Home.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("todo")
@RequiredArgsConstructor
public class TodoController {
	
	private final TodoService service;
	
	@GetMapping("/test1")
	public ResponseEntity<?> testTodo () {
		String str = service.test();
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String>response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/test2")
	public ResponseEntity<?> testTodoservice () {
		String str = service.testService();
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String>response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping("/post")
	public ResponseEntity<?> createTodo (@RequestBody TodoDTO dto) {
		try {
			//임시 유저id
			String tempraryUserId = "temporary-user";
			
			//TodoDTO객체를 TodoEntity객체로 변환한다.
			TodoEntity entity = TodoDTO.toEntity(dto);
			System.out.println(entity);
			
			//id 값을 명시적으로 null로 설정하여, 엔티티가 새로운 데이터임을 보장합니다.
			entity.setId(null);
			
			//임시 유저id를 설정해준다.
			entity.setUserId(tempraryUserId);
			
			List<TodoEntity> entities = service.create(entity);
			
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {
			
			String error = e.getMessage();
			
			ResponseDTO<TodoDTO>response = ResponseDTO.<TodoDTO>builder().error(error).build();
			
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping("/get")
	public ResponseEntity<?> retrieveTodoList(){
		String temporaryUserId = "temporary-user";
		
		//서비스레이어의 retrieve메서드를 이용해 Todo리스트를 반환받아 entities에 저장한다.
		List<TodoEntity> entities = service.retrieve(temporaryUserId);
		
		//자바 스트림을 이용해 반환된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		//변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/put")
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){
		String temporaryUserId = "temporary-user";
		
		//dto를 Entity로 변환한다.
		TodoEntity entity = TodoDTO.toEntity(dto);
		
		entity.setUserId(temporaryUserId);
		
		//서비스레이어의 update메서드를 이용해 entity를 업데이트한다.
		List<TodoEntity> entities = service.update(entity);
		
		//자바 스트림을 이용해 반환된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		//변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping("/delet")
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
		try {
			String tempraryUserId = "temporary-user";
			TodoEntity entity = TodoDTO.toEntity(dto);
			entity.setUserId(tempraryUserId);
			
			List<TodoEntity> entities = service.delete(entity);
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}