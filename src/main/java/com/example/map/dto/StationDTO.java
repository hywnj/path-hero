package com.example.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // JSON 데이터에서 DTO에 정의되지 않은 필드는 무시
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StationDTO {
    // result > path > subPath > passStopList > stations
    private int index; // 역/정류장 순번
    private int stationID; // 역/정류장 ID
    private String stationName; // 역/정류장 명칭
    private String x; // 역/정류장 X좌표
    private String  y; // 역/정류장 X좌표

    private String isNonStop; // 미정차 정류장 여부 Y/N(버스인 경우에만 필수)
}
