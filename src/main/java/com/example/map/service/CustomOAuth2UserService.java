//5.
package com.example.map.service;

import com.example.map.dto.*;
import com.example.map.entity.UserEntity;
import com.example.map.repository.UserRepository;
import com.example.oauthjwt.dto.*;
import com.example.oauthjwt.entity.UserEntity;
import com.example.oauthjwt.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    //10. SecurityConfig의 oauth2login메소드에 등록을 하여 사용
    // DefaultOAuth2UserService : 유저정보를 획득하기 위한 함수들

    //17.
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2UserRequest : 제공되는 유저정보

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // registrationId : 구글인지 네이버인지 확인되는 값

        //9.
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        // 12. 리소스 서버에서 받은 데이터를 OauthService에 전달되면 OauthUser의 dto에 담아 프로바이드에 전달해주면 로그인 완료
        // 리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬 (프로바이더와 해당 id 값을 받아옴)
        String username = oAuth2Response.getProvider() + " " + oAuth2Response.getUsername();

        //17-1.
        UserEntity existData = userRepository.findByUsername(username); // 해당 유저가 존재하는지 조회


        if (existData == null) { // 한번도 로그인을 하지 않은 경우

            UserEntity userEntity = new UserEntity(); //새로운 데이터를 삽입
            userEntity.setUsername(username);
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setName(oAuth2Response.getName());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity); // 새로운 데이터 저장

            //12-1.
            UserDTO userDTO = new UserDTO(); //UserDTO에 데이터 담기
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);
        }
        else { // 한번이라도 로그인을 해서 데이터가 존재하는 경우

            existData.setEmail(oAuth2Response.getEmail()); // 데이터 업데이트
            existData.setName(oAuth2Response.getName());

            userRepository.save(existData);

            // 12-1.
            UserDTO userDTO = new UserDTO(); // 특정한 dto에 담아 CustomOAuth2User에 넘겨주면 로그인 완료
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());

            return new CustomOAuth2User(userDTO);
        }

    }
}