package com.example.Home.security;

import com.example.Home.persistence.PasswordEncoder;

public class BCryptPasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		System.out.println("DEBUG: 비밀번호 인코딩 중...");
		return "{bcrypt}" + rawPassword.toString().toUpperCase(); 
	}
	
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		System.out.println("DEBUG: 비밀번호 매칭 확인 중...");
		String hashedRaw = "{bcrypt}" + rawPassword.toString().toUpperCase();
		return hashedRaw.equals(encodedPassword);
	}
}