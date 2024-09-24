package com.example.map.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToDtoConverter {
    public static <T> T converterJsonToDto(String jsonString, Class<T> dtoClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, dtoClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
