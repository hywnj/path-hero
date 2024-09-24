package com.example.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON 데이터에서 DTO에 정의되지 않은 필드는 무시
public class SubPathDTO {
    // result > path > subPath
    private int trafficType; // 이동 수단 종류(1: 지하철, 2: 버스, 3: 도보)
    private int distance; // 이동 거리
    private int sectionTime; // 이동 소요 시간

    // 버스 | 지하철
    private int stationCount; // 이동하여 정차하는 정거장 수
    private String startName; // 승차 정류장/역명
    private String startX;
    private String startY;
    private String endName; // 하차 정류장/역명
    private String endX;
    private String endY;
    private List<StationDTO> station; // 경로 상세구간 역/정류장 정보 리스트

    private List<LaneDTO> lane; // 지하철/버스 정보

    // 지하철
    private String way; // 지하철 방면
    private String wayCode; // 지하철 방면 정보 코드 (1: 상행, 2: 하행) - 지하철 첫번째 경로에만 필수
    private String door; // 지하철 빠른 환승 위치
    private String startExitNo; // 지하철 들어가는 출구 번호 (없을 수도 있음)
    private String startExitX;
    private String startExitY;

    // 버스
    private int startStationCityCode; // 출발 정류장 도시코드
    private int startStationProviderCode; // 출발 정류장 BIS 코드 (BIS 제공지역인 경우에만 필수)
    private String startLocalStationID; // 각 지역 출발 정류장 ID (BIS 제공지역인 경우에만 필수)
    private String startArsID; // 각 지역 출발 정류장 고유번호 (BIS 제공지역인 경우에만 필수)

    private int endStationCityCode; // 도착 정류장 도시코드
    private int endStationProviderCode; // 도착 정류장 BIS 코드 (BIS 제공지역인 경우에만 필수)
    private String endLocalStationID; // 각 지역 도착 정류장 ID (BIS 제공지역인 경우에만 필수)
    private String endArsID; // 각 지역 도착 정류장 고유번호 (BIS 제공지역인 경우에만 필수)
}
