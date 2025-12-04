package com.example.demo.model.controller;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("test")
public class TestController {
	
	@GetMapping("/Exam")
	public String testController() {
		return "Hello World testGetMapping";
	}
	
	@GetMapping("/Hello")
	public String test() {
	    return "Hello Spring Boot";
	}
	
	@GetMapping("/{id}")
	public String getUserId(@PathVariable(required = false) Optional<Integer> id) {
		return "User ID: " + id.orElse(null);
	}
	
	@GetMapping("/users")
	public String getUserById(@RequestParam(value="id", defaultValue = "0") Long userId) {
		return "User ID: " + userId;
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("query") String query, @RequestParam("page") int page) {
		return "Search query: " + query + ", Page: " + page;
	}
	
	@GetMapping("/testRequestBody")
    //@RequestBody TestRequestBodyDTO testRequestBodyDTO
    //RequestBody로 날아오는 JSON을 TestRequestBody객체로 변환해서 가져와라
    //날아오는 JSON의 형식은 TestRequestBody의 형태와 같아야 할 것이다.
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
		return "Hello World! ID : "+testRequestBodyDTO.getId() + " Message : " + testRequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testControllerResponseBody(){
		List<String> list = new ArrayList<>();
		list.add("Hellow World! I'm ResponseDTO");
		list.add("너무 건조해요 히터좀 그만");
		ResponseDTO<String>response = ResponseDTO.<String>builder().data(list).build();
		return response;
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity() {
		List<String> list = new ArrayList<>();
		list.add("Hellow World! I'm ResponseEntity. And you got what?");
		ResponseDTO<String>response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);		
	}
	
}
