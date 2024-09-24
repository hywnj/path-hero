package com.example.map.service;

import com.example.map.client.NaverSearchClient;
import com.example.map.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final NaverSearchClient naverSearchClient;

    public Map<String, Object> searchLocation(String query){
        if (query == null || query.equals("")) {
            throw new BadRequestException("위치를 검색해주세요!");
        }
        Map<String, Object> response = naverSearchClient.searchLocation(query);
        return response;
    }
}
