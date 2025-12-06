package com.example.demo.model.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private List<T> data;  // 실제 데이터
    private String error;  // 에러 메시지 (없으면 null)
}