package com.example.tourweb.service.crud;

import com.example.tourweb.model.ChangeInfoRequest;
import com.example.tourweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    private UserRepository userRepository;

    public void changeInfo(ChangeInfoRequest changeInfoRequest){
        userRepository.updateInfoUser(changeInfoRequest.getTel(),changeInfoRequest.getFullName(), changeInfoRequest.getGender(), changeInfoRequest.getCountry(), changeInfoRequest.getDateOfBirth(), changeInfoRequest.getUsername());
    }

}
