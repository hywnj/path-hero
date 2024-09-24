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
public class PathDTO {
    // result > path
    private int pathType; // 결과 종류 (1: 지하철, 2: 버스, 3: 버스+지하철)

    // path > info
    @JsonProperty("info.trafficDistance")
    private double trafficDistance; // 도보 제외 총 이동거리

    @JsonProperty("info.totalWalk")
    private int totalWalk; // 총 도보 이동 거리

    @JsonProperty("info.totalTime")
    private int totalTime; // 총 소요 시간

    @JsonProperty("info.payment")
    private int payment; // 총 요금

    @JsonProperty("info.busTransitCount")
    private int busTransitCount; // 버스 환승 카운트

    @JsonProperty("info.subwayTransitCount")
    private int subwayTransitCount; // 지하철 환승 카운트

    @JsonProperty("info.totalStationCount")
    private int totalStationCount; // 총 정류장 합

    @JsonProperty("info.busStationCount")
    private int busStationCount; // 버스 정류장 합

    @JsonProperty("info.subwayStationCount")
    private int subwayStationCount; // 지하철 정류장 합

    @JsonProperty("info.firstStartStation")
    private String firstStartStation; // 최초 출발역/정류장
    @JsonProperty("info.lastEndStation")
    private String lastEndStation; // 최종 도착역/정류장

    @JsonProperty("info.totalDistance")
    private double totalDistance; // 해당 Path의 총 거리

    private List<SubPathDTO> subPath; // Path 상세 정보(Sub Path) 리스트
}
