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
	private int id;
	private String title;
	private boolean done;
	private int itemOrder;
	
	public TodoDTO (final TodoEntity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
		this.itemOrder = entity.getItemOrder();
		
	}
	
	public static TodoEntity toEntity(TodoDTO dto) {
		return TodoEntity.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.done(dto.isDone())
				.itemOrder(dto.getItemOrder())
				.build();
	}
}
