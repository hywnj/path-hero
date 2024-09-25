//14.
package com.example.map.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {

    private final UserDTO userDTO;
    public CustomOAuth2User(UserDTO userDTO) {
        this.userDTO = userDTO;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return null; // 구글과 네이버의 attributes의 값들이 다르기 때문에 null 처리
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userDTO.getRole()));  // 사용자 역할 반환
        return authorities;
    }

    @Override
    public String getName() {
        return userDTO.getName();
    }

   public String getUsername() {
        return userDTO.getUsername();
   }



}
