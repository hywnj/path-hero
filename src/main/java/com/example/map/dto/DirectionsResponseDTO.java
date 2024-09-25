package com.example.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON 데이터에서 DTO에 정의되지 않은 필드는 무시
public class DirectionsResponseDTO {
    private int searchType; // 결과 구분 (0: 도시내, 1: 도시간)
    private int outTrafficCheck; // 도시간 "직통" 탐색 결과 유무(환승 X) (0: false, 1: true)
    private int busCount; // 버스 결과 개수
    private int subwayCount; // 지하철 결과 개수
    private int subwayBusCount; // “버스+지하철” 결과 개수
    private double pointDistance; // 출발지(SX, SY)와 도착지(EX, EY)의 직선 거리 (미터)
    @JsonProperty("path")
    private List<PathDTO> path; // 경로 리스트
}
