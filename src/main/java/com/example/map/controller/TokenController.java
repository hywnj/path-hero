package com.example.map.controller;

import com.example.map.jwt.JWTUtil;
import com.example.oauthjwt.jwt.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {

    private final JWTUtil jwtUtil;

    public TokenController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@CookieValue("RefreshToken") String refreshToken) {
        if (jwtUtil.isExpired(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired");
        }

        String username = jwtUtil.getUsername(refreshToken);
        String newAccessToken = jwtUtil.createJwt(username, "ROLE_USER", 60*60*60L); // 새로운 액세스 토큰 발급

        // 새로운 액세스 토큰을 반환
        return ResponseEntity.ok().header("Authorization", newAccessToken).build();
    }
}
