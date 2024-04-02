package com.example.tourweb.service.crud;

import com.example.tourweb.entity.User;
import com.example.tourweb.model.ChangePasswordRequest;
import com.example.tourweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public void updatePassword(String username,String password){
        userRepository.updatePassword(password,username);
    }

    public String changePassword(ChangePasswordRequest changePasswordRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!passwordEncoder.matches(changePasswordRequest.getPassword(), userDetails.getPassword()))
            return "Mật khẩu không đúng!";
        if (changePasswordRequest.getPassword().equals(changePasswordRequest.getNewPassword()))
            return "Mật khẩu mới không được trùng";
        User user = userRepository.findByUsername(userDetails.getUsername()).orElse(null);
        if (user != null){
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
            userRepository.updatePassword(user.getPassword(), user.getUsername());
        }
        return "success";
    }
}
