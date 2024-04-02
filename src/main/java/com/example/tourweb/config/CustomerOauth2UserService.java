package com.example.tourweb.config;

import com.example.tourweb.entity.User;
import com.example.tourweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        CustomerOauth2User customerOAuth2User = new CustomerOauth2User(super.loadUser(userRequest));
        User userOptional = userRepository.findByUsername(customerOAuth2User.getName()).orElse(null);
        if(userOptional == null){
            var role = "USER";
            var user = User.builder()
                    .username(customerOAuth2User.getName().toLowerCase())
                    .email(customerOAuth2User.getName()+"@gmail.com")
                    .password(UUID.randomUUID().toString())
                    .money(0)
                    .roles(role)
                    .bills(null)
                    .build();
            userRepository.save(user);
        }
        return new CustomerOauth2User(super.loadUser(userRequest));
    }
}
