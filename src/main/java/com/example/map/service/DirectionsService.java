package com.example.map.service;

import com.example.map.client.DirectionsSearchClient;
import com.example.map.dto.DirectionsRequestDTO;
import com.example.map.dto.DirectionsResponseDTO;
import com.example.map.exception.MapException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
@Service
@RequiredArgsConstructor
public class DirectionsService {
    private final DirectionsSearchClient directionsSearchClient;

    /**
     * 길찾기 List<DirectionsResponseDTO>
     */
    public <T> T searchDirections(DirectionsRequestDTO requestDTO) {
        // 유효성 검사
        if (requestDTO.getMapX() == 0 || requestDTO.getMapY() == 0
                || requestDTO.getStartX() == 0 || requestDTO.getStartY() == 0
                || requestDTO.getEndX() == 0 || requestDTO.getEndY() == 0) {
            // @TODO 에러 처리
            return null;
            // throw new MapException("위치 정보를 모두 입력해주세요.");
        }

        double startX = requestDTO.getStartX();
        double startY = requestDTO.getStartY();
        double endX = requestDTO.getEndX();
        double endY = requestDTO.getEndY();

        // Open API 호출
        try {
            // Map<String, Object> response = directionsSearchClient.searchDirections(startX, startY, endX, endY);

            // Open API 에 공인 IP 등록해야해서 테스트로 넣어둠
            String jsonFileName = (requestDTO.getChoice() == 1) ? "bangToHangang.json" : (requestDTO.getChoice() == 2 ? "hangangToHome.json" : "sample.json") ;
            ClassPathResource resource = new ClassPathResource(jsonFileName);

            // ObjectMapper를 사용하여 JSON 파일을 읽고 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            DirectionsResponseDTO routeResponse;
            try (InputStream inputStream = resource.getInputStream()) {
                routeResponse = objectMapper.readValue(inputStream, DirectionsResponseDTO.class);
            }

            // 변환된 객체 사용
            // System.out.println(routeResponse);

            return (T) routeResponse;

        } catch (IOException e) {
            throw new MapException(e.getMessage());
        }
    }

}
