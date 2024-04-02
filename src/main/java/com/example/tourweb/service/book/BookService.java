package com.example.tourweb.service.book;

import com.example.tourweb.entity.Bill;
import com.example.tourweb.model.BillRequest;
import com.example.tourweb.repository.BillRepository;
import com.example.tourweb.repository.UserRepository;
import com.example.tourweb.service.crud.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    public String resolveBook(BillRequest orderRequest){
        LocalDate localDate = LocalDate.now();
        ZonedDateTime now = ZonedDateTime.now();
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime startOfDayInDefaultZone = startOfDay.atZone(now.getZone());
        Date date = Date.from(startOfDayInDefaultZone.toInstant());
        if(!date.before(orderRequest.getArrivalsDate()) || !date.before(orderRequest.getLeavingDate()) || !orderRequest.getArrivalsDate().before(orderRequest.getLeavingDate())){
            return "Thời gian không hợp lệ";
        }
        var user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        user.setMoney(user.getMoney() - orderRequest.getTotalMoney());
        var bill = Bill.builder()
                .leavingDate(orderRequest.getLeavingDate())
                .arrivalsDate(orderRequest.getArrivalsDate())
                .totalMoney(orderRequest.getTotalMoney())
                .whereTo(orderRequest.getWhereTo())
                .guests(orderRequest.getGuests())
                .user(user)
                .build();
        billRepository.save(bill);
        return "success";
    }

    public List<Bill> getAllBillByUser_Username(String username){
        return billRepository.getBillByUser_Username(username);
    }
}
