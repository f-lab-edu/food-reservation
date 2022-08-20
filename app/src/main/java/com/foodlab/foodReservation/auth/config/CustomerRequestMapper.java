package com.foodlab.foodReservation.auth.config;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerRequestMapper {

    public UserInfo toDto(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return UserInfo.builder()
                .email((String)attributes.get("email"))
                .build();
    }

}
