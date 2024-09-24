package com.example.map.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

import java.util.Map;

@Component
@Slf4j
public class NaverSearchClient {
    @Value("${NAVER_CLIENT_ID}")
    private String NAVER_CLIENT_ID;

    @Value("${NAVER_CLIENT_SECRET}")
    private String NAVER_CLIENT_SECRET;

    public Map<String, Object> searchLocation(String query) {
        log.info("NaverSearchClient::search");

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", query)
                .queryParam("display", 5)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", NAVER_CLIENT_ID)
                .header("X-Naver-Client-Secret", NAVER_CLIENT_SECRET)
                .build();

        log.info("uri : {}", uri);

        RestTemplate restTemplate = new RestTemplate();
        try {
            // API 요청을 수행하고 응답 받기
            ResponseEntity<Map> result = restTemplate.exchange(req, Map.class);

            // 응답 본문이 null인 경우를 처리
            if (result.getBody() == null) {
                return null;
            }

            log.info("API 응답 결과: {}", result.getBody());
            return result.getBody();  // 정상적으로 응답 본문 반환

        } catch (Exception e) {
            // 예외 발생 시 처리
            throw new RuntimeException("API 호출 중 오류 발생: ", e);
        }
    }
}
