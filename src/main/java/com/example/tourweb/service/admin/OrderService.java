package com.example.tourweb.service.admin;


import com.example.tourweb.entity.Bill;
import com.example.tourweb.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBill(){
        return billRepository.findAll();
    }
}
