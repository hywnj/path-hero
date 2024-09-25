package com.example.map.dto;

import lombok.Data;

import java.util.List;

@Data
public class PassStopListDTO {
    private List<StationDTO> stations; // 정류장 정보 그룹노드
}
