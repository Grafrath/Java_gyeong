package com.example.Home.dto;

import com.example.Home.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
	private String title;
	private boolean done;
	
	public TodoDTO (final TodoEntity entity) {
		this.title = entity.getTitle();
		this.done = entity.isDone();
	}
	
	public static TodoEntity toEntity(TodoDTO dto) {
		return TodoEntity.builder()
				.title(dto.getTitle())
				.done(dto.isDone())
				.build();
	}
}
