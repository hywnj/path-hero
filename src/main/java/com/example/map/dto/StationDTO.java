package com.example.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON 데이터에서 DTO에 정의되지 않은 필드는 무시
public class StationDTO {
    // result > path > subPath > passStopList > stations
    private int index; // 역/정류장 순번
    private int stationID; // 역/정류장 ID
    private String stationName; // 역/정류장 명칭
    private int stationX;
    private int stationY;

    private String isNonStop; // 미정차 정류장 여부 Y/N(버스인 경우에만 필수)
}
