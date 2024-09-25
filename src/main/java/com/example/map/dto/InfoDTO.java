package com.example.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // JSON 데이터에서 DTO에 정의되지 않은 필드는 무시
public class InfoDTO {
    // result > path > info
    private double trafficDistance; // 도보 제외 총 이동거리
    private int totalWalk; // 총 도보 이동 거리
    private int totalTime; // 총 소요 시간
    private int payment; // 총 요금
    private int busTransitCount; // 버스 환승 카운트
    private int subwayTransitCount; // 지하철 환승 카운트
    private int totalStationCount; // 총 정류장 합
    private int busStationCount; // 버스 정류장 합
    private int subwayStationCount; // 지하철 정류장 합
    private String firstStartStation; // 최초 출발역/정류장
    private String lastEndStation; // 최종 도착역/정류장
    private String mapObj; // 보간점 API | 경로그래프 정보를 호출하기 위한 파라미터 값
    private double totalDistance; // 해당 Path의 총 거리
}
