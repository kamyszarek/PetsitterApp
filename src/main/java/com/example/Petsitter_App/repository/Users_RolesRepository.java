package com.example.Petsitter_App.repository;

import com.example.Petsitter_App.model.Users_roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Users_RolesRepository extends JpaRepository<Users_roles, Long> {

    @Query("SELECT u FROM Users_roles u WHERE user_id = ?1")
    List<Users_roles> getByUser_Id(long user_id);

    @Query("SELECT u FROM Users_roles u WHERE user_id = ?1")
    Users_roles getRolesByUserId(long user_id);
}
