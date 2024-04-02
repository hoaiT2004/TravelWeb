package com.example.tourweb.service.admin;

import com.example.tourweb.config.MyUserDetails;
import com.example.tourweb.entity.User;
import com.example.tourweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;
    public List<User> getAllAccount(){
        return userRepository.findAllByRolesEquals("USER");
    }
}
