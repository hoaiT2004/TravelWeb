package com.example.tourweb.repository;

import com.example.tourweb.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
    List<Bill> getBillByUser_Username(String username);
}
