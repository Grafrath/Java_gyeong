package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

//@Configuration: 이 클래스가 설정파일임을 알려주는 용도의 어노테이션
//@ComponentScan : 자동으로 컴포넌트 클래스를 검색하여 컴포넌트와 빈 클래스를 등록함
//@EnableAutoConfiguration : 스프링의 다양한 설정이 자동으로 구성되고 완료됨