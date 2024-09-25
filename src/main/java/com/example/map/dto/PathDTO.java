package com.example.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // JSON 데이터에서 DTO에 정의되지 않은 필드는 무시
public class PathDTO {
    // result > path
    private int pathType; // 결과 종류 (1: 지하철, 2: 버스, 3: 버스+지하철)
    private InfoDTO info; // 요약 정보 확장 노드
    private List<SubPathDTO> subPath; // Path 상세 정보(Sub Path) 리스트
}
