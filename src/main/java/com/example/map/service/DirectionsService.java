package com.example.map.service;

import com.example.map.client.DirectionsSearchClient;
import com.example.map.converter.JsonToDtoConverter;
import com.example.map.dto.DirectionsRequestDTO;
import com.example.map.dto.DirectionsResponseDTO;
import com.example.map.exception.MapException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DirectionsService {
    private final DirectionsSearchClient directionsSearchClient;

    /**
     * 길찾기 List<DirectionsResponseDTO>
     */
    public Map<String, Object> searchDirections(DirectionsRequestDTO requestDTO) {
        // 유효성 검사
        if (requestDTO.getStartX() == null || requestDTO.getStartX().isBlank()
        || requestDTO.getStartY() == null || requestDTO.getStartY().isBlank()
        || requestDTO.getEndX() == null || requestDTO.getEndX().isBlank()
        || requestDTO.getEndY() == null || requestDTO.getEndY().isBlank()) {
            throw new MapException("위치 정보를 모두 입력해주세요.");
        }

        double startX = Double.parseDouble(requestDTO.getStartX());
        double startY = Double.parseDouble(requestDTO.getStartY());
        double endX = Double.parseDouble(requestDTO.getEndX());
        double endY = Double.parseDouble(requestDTO.getEndY());

        // Open API 호출
        try {
            Map<String, Object> response = directionsSearchClient.searchDirections(startX, startY, endX, endY);
            return response;

        } catch (IOException e) {
            throw new MapException(e.getMessage());
        }
    }

}
