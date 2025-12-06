package com.example.Home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@GetMapping("/users")
	public String getUserById(@RequestParam(value = "id", required = false) Long userId) {
		if (userId == null) {
	        return "No user ID provided";
	    }
		return "User ID: " + userId;
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("query") String query, 
	                     @RequestParam("page") int page) {
	    return "Search query: " + query + ", Page: " + page;
	}
}
