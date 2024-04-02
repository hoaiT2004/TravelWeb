package com.example.tourweb.service.crud;

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
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByUsername(username);
        if(!optional.isPresent()) throw new UsernameNotFoundException("Could not find user by username");
        User user = optional.get();
        return new MyUserDetails(user);
    }

    public void updateMoneyUser(int money,String username){
        userRepository.updateMoney(money,username);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }


}
