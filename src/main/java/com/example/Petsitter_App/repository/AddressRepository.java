package com.example.Petsitter_App.repository;

import com.example.Petsitter_App.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT u FROM Address u WHERE id = ?1")
    public Address findAddressById(Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Address a WHERE a.id > ?1")
    void deleteBiggerThan(long i);

    @Query("SELECT a FROM Address a WHERE city = ?1")
    public List<Address> findAllByCity(String city);

    @Transactional
    @Modifying
    @Query("UPDATE Address a SET a.country = ?2, a.state = ?3, a.city = ?4, a.street = ?5," +
            " a.house_no = ?6, a.flat_no = ?7 WHERE a.id = ?1 ")
    void updateAddress(long id, String country, String state, String city, String street, String house_no, String flat_no);


}
