package com.example.demo.model.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
	private String id;
	private String title;
	private boolean done;
	
	public TodoDTO(final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
	}
}

//서비스가 요청을 처리하고 클라이언트로 반환할 때, 모델 자체를 그래도 반환하는 경우는 별로 없다.
//보통은 데이터를 전달하기 위해 사용하는 객체인 DTO로 변환해 반환한다.

//DTO로 변환하여 반환하는 이유
//비즈니스 로직을 캡슐화 하기 위함, 다른 객체로 바꿔 반환하면 외부 사용자에게 서비스 내부의 로직, DB 구조등을 숨길 수 있다.
//클라이언트가 필요한 정보를 모델이 전부 포함하지 않는 경우
//대표적으로 에러 메시지가 있다, 만약 서비스 실행 도중 유저 에러가 나면 이 에러 메시지를 어디에 포함해야 하는가?
//모델은 서비스 로직과는 관련이 없기 때문에 모델에 담기는 애매하다.
//이런 경우 DTO에 에러 메시지 필드를 선언하고 DTO에 포함하면 된다.