package com.foodlab.foodReservation.customer.controller;

import com.foodlab.foodReservation.auth.token.JwtProvider;
import com.foodlab.foodReservation.auth.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final JwtProvider jwtProvider;

    @GetMapping("/token/refresh")
    public Map<String, String> refreshAuth(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("refreshToken");

        if (token != null && jwtProvider.verifyToken(token)) {
            String email = jwtProvider.getCustomerEmail(token);
            Token newToken = jwtProvider.generateToken(email, "USER");

            response.addHeader("accessToken", newToken.getAccessToken());
            response.addHeader("refreshToken", newToken.getRefreshToken());
            response.setContentType("application/json;charset=UTF-8");
            Map<String, String> result = new HashMap<>();
            result.put("result", "success");
            return result;
        }

        throw new RuntimeException();
    }

}
