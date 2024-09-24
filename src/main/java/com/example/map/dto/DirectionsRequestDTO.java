package com.example.map.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectionsRequestDTO {
    private double mapX; // 업체, 기관이 위치한 장소의 x 좌표(KATECH 좌표계 기준)
    private double mapY; // 업체, 기관이 위치한 장소의 y 좌표(KATECH 좌표계 기준)

    private double startX; // 출발지 x좌표 (경도)
    private double startY; // 출발지 y좌표 (위도)
    private double endX; // 도착지 x좌표 (경도)
    private double endY; // 도착지 y좌표 (위도)
    // private String option; // 경로검색결과 정렬방식 (0: 추천경로, 1 타입별정렬)
    // private String searchType; // 0: 도시내 이동, 1: 도시간 이동
    // private String searchPathType; // 도시 내 경로수단 (0:모두(default), 1:지하철, 2:버스)
}
