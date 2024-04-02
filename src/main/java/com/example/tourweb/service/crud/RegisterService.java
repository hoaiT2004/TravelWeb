package com.example.tourweb.service.crud;

import com.example.tourweb.entity.User;
import com.example.tourweb.model.RegisterRequest;
import com.example.tourweb.model.UserDto;
import com.example.tourweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto register(RegisterRequest registerRequest){
        User user1 = userRepository.findByUsername(registerRequest.getUsername()).orElse(null);
        if(user1 != null) return null;
        String inviteCode = registerRequest.getInviteCode();
        int money = 0;
        if(inviteCode != null){
            if(!inviteCode.equalsIgnoreCase("admin")) {
                Optional<User> loadUserAsCode = userRepository.findByUsername(inviteCode);
                if (loadUserAsCode.isPresent()) money = 40;
            }
        }
        String role;
        if(registerRequest.getUsername().equalsIgnoreCase("admin")) role = "ADMIN";
        else role = "USER";
        var user = User.builder()
                .username(registerRequest.getUsername().toLowerCase())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .fullName(registerRequest.getFullName())
                .email(registerRequest.getEmail())
                .linkAvatar("/images/avatar/default-image.jfif")
                .gender(registerRequest.getGender())
                .country(registerRequest.getCountry())
                .dateOfBirth(registerRequest.getDateOfBirth())
                .money(money)
                .tel(registerRequest.getTel())
                .inviteCode(registerRequest.getInviteCode())
                .roles(role)
                .bills(null)
                .build();
        userRepository.save(user);
        return new UserDto(user);
    }
}
