package com.example.map.controller;

import com.example.map.dto.DirectionsRequestDTO;
import com.example.map.service.DirectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/directions")
@RequiredArgsConstructor
public class DirectionsController {
    private final DirectionsService directionsService;

    @PostMapping("/search")
    public ResponseEntity<?> searchDirections(@RequestBody DirectionsRequestDTO request) {
        return ResponseEntity.ok(directionsService.searchDirections(request));
    }
}
