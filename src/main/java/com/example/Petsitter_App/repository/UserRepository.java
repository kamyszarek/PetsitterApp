package com.example.Petsitter_App.repository;

import com.example.Petsitter_App.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE user_id = ?1")
    public User findUserById(Long id);

    @Query("SELECT u FROM User u WHERE login = ?1")
    public User findByLogin(String login);

    @Query("SELECT u FROM User u WHERE email = ?1")
    public User findByEmail(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.enabled = ?1 where u.email = ?2")
    void activateUser(boolean x, String email);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2 where u.login = ?1")
    void changePassword(String login, String password);

    @Query("SELECT u FROM User u WHERE address_id = ?1")
    User findUserByAddressId(Long y);
}
