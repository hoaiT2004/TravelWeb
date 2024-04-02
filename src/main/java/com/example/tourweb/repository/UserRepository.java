package com.example.tourweb.repository;

import com.example.tourweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);

    List<User> findAllByRolesEquals(String role);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.fullName = :fullName,u.tel = :tel, u.gender = :gender,u.country = :country,u.dateOfBirth = :dateOfBirth WHERE u.username = :username")
    void updateInfoUser(String tel,String fullName, String gender, String country, Date dateOfBirth, String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.username = :username")
    void updatePassword(String password,String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.money = :money WHERE u.username = :username")
    void updateMoney(int money,String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.username = :username")
    void updateEmail(String email,String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.linkAvatar = :linkAvatar WHERE u.username = :username")
    void updateLinkAvatar(String linkAvatar,String username);


}
