package com.example.Petsitter_App.service;

import com.example.Petsitter_App.model.User;
import com.example.Petsitter_App.repository.UserRepository;
import com.example.Petsitter_App.security.UsersDetails;
import com.example.Petsitter_App.model.User;
import com.example.Petsitter_App.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUsersDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UsersDetails(user);
    }

}
