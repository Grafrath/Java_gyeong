package com.example.Home.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*")
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
	
	@PostMapping("/createTodo")
	public ResponseEntity<?> createTodo (@RequestBody TodoDTO dto) {
		try {
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			List<TodoEntity> entities = service.create(entity);
			List<TodoDTO> dtos = entities.stream().map( e -> new TodoDTO(e)).collect(Collectors.toList());
			
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			return ResponseEntity.ok().body(response);
			
		} catch (Exception e) {			
			ResponseDTO<TodoDTO>response = ResponseDTO.<TodoDTO>builder().error(e.getMessage()).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping("/retrieveTodoList")
	public ResponseEntity<?> retrieveTodoList(){
	
		List<TodoEntity> entities = service.retrieve();
		List<TodoDTO> dtos = new ArrayList<TodoDTO>();
		
		for (TodoEntity e : entities) {
			dtos.add(new TodoDTO(e));
		}
		
		//변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping("/updateTodo")
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){
		TodoEntity entity = TodoDTO.toEntity(dto);
		
		List<TodoEntity> entities = service.update(entity);
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping("/deleteTodo")
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
		try {
			TodoEntity entity = TodoDTO.toEntity(dto);
			
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