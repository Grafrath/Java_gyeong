package com.example.Home.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Home.dto.ResponseDTO;
import com.example.Home.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("test")
public class TestController {
	
	@GetMapping("/testGetMapping")
	public String testGetMapping() {
		return "겟 메핑 테스트 완료";
	}
	
	@GetMapping("/users/{id:\\\\d+}")
	public String getUserById(@PathVariable("id") Long id) {
	    return "User ID: " + id;
	}
	
	@GetMapping({"/", "/{id:\\\\d+}"})
	public String testControllerWidthPathVariables(@PathVariable(required=false)Integer  id) {
		return "Hello world! ID: "+id;
	}
	
	@GetMapping("/testRequestParam")
	public String testControllerRequestParam(@RequestParam(required=false)Integer id) {
		return "Hello World! ID: " + id;
	}
	
	@GetMapping("/testRequestBody")
	public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testrequestBodyDTO) {
		return "Hello World! ID: "+testrequestBodyDTO.getId() + " Message : " + testrequestBodyDTO.getMessage();
	}
	
	@GetMapping("/testResponseBody")
	public ResponseDTO<String> testResponseBody2(@RequestBody TestRequestBodyDTO dto){
		List<String> list = new ArrayList<>();
		list.add("Hellow World! I'm ResponseDTO");
		ResponseDTO<String>response = ResponseDTO.<String>builder().data(list).build();
		return response;
	}
	
	@GetMapping("/testResponseEntity")
	public ResponseEntity<?> testControllerResponseEntity(){
		List<String> list = new ArrayList<>();
		list.add("Hellow World! I'm ResponseEntity. And you got what?");
		ResponseDTO<String>response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
	
}
