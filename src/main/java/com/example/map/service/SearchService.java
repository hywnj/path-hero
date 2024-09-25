package com.example.map.service;

import com.example.map.client.NaverSearchClient;
import com.example.map.exception.BadRequestException;
import com.example.map.exception.MapException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final NaverSearchClient naverSearchClient;

    public Map<String, Object> searchLocation(String query){
        if (query == null || query.equals("")) {
            // @TODO 에러 처리
            return null;
        }
        Map<String, Object> response = naverSearchClient.searchLocation(query);
        return response;
    }
}
