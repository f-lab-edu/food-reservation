package com.foodlab.foodReservation.auth.config;

import com.foodlab.foodReservation.customer.entity.Customer;
import com.foodlab.foodReservation.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final CustomerRepository customerRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2Attribute oAuth2Attribute = OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        //System.out.println("oAuth2Attribute.getAttributes() = " + oAuth2Attribute.getAttributes());

        Map<String, Object> customerAttribute = oAuth2Attribute.convertToMap();
        Customer customer = saveCustomer(oAuth2Attribute);
        httpSession.setAttribute("customer", customer);

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER")), customerAttribute, "email");
    }

    private Customer saveCustomer(OAuth2Attribute oAuth2Attribute) {
        Customer customer = customerRepository.findByEmail(oAuth2Attribute.getEmail());
        if (customer == null) {
            return customerRepository.save(new Customer(oAuth2Attribute.getEmail()));
        }
        return customer;
    }
}