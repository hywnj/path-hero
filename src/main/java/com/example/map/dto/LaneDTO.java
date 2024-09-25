package com.example.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // JSON 데이터에서 DTO에 정의되지 않은 필드는 무시
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LaneDTO {
    private int subwayCode; // 지하철 노선 번호
    private String name; // 지하철 노선명
    private String subwayCityCode; // 지하철 도시코드

    private String busNo; // 버스 번호
    private String type; // 버스노선 종류
    private int busID; // 버스 코드
    private String busLocalBlID; // 각 지역 버스노선 ID (BIS 제공지역인 경우에만 필수)
    private int busCityCode; // 운수회사 승인 도시코드 (버스인 경우에만 필수)
    private int busProviderCode; // BIS 코드 (BIS 제공지역인 경우에만 필수)
}
