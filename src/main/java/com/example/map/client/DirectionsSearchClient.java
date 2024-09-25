package com.example.map.client;

import com.example.map.exception.MapException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

@Service
@Slf4j
public class DirectionsSearchClient {

    @Value("${ODSAY_API_KEY}")
    private String ODSAY_API_KEY;
    private final String ODSAY_BASE_URL = "https://api.odsay.com/v1/api";

    public Map<String, Object> searchDirections(double startX, double startY, double endX, double endY) throws IOException {
        log.info("DirectionsSearchClient::searchDirections");

        // String urlInfo = ODSAY_BASE_URL + "/searchPubTransPathT?SX=" + startX +
                //"&SY=" + startY + "&EX=" + endX + "&EY=" + endY +
                //"&apiKey=" + URLEncoder.encode(ODSAY_API_KEY, "UTF-8");

        URI uri = UriComponentsBuilder
                .fromUriString(ODSAY_BASE_URL)
                .path("/searchPubTransPathT")
                .queryParam("SX", startX)
                .queryParam("SY", startY)
                .queryParam("EX", endX)
                .queryParam("EY", endY)
                .queryParam("apiKey", URLEncoder.encode(ODSAY_API_KEY, "UTF-8"))
                .build()
                .toUri();

        log.info(uri.getQuery());

        RequestEntity<Void> req = RequestEntity.get(uri).build();

        RestTemplate restTemplate = new RestTemplate();
        // API 요청을 수행하고 응답 받기
        ResponseEntity<Map> result = restTemplate.exchange(req, Map.class);

        // 응답 본문이 null인 경우를 처리
        if (result.getBody() == null) {
            // throw new MapException("길찾기 결과가 없습니다.");
            // @TODO 에러 처리
            return null;
        }

        log.info("API 응답 결과: {}", result.getBody());
        return result.getBody();  // 정상적으로 응답 본문 반환
    }
}
