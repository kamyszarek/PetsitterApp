package com.example.Petsitter_App.repository;

import com.example.Petsitter_App.model.ConfirmNewAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CNARepository extends JpaRepository<ConfirmNewAccount, Long> {

    @Query(value="SELECT * FROM confirm_new_account WHERE u_email = ?1", nativeQuery = true)
    public ConfirmNewAccount findByEmail(String email);

}
