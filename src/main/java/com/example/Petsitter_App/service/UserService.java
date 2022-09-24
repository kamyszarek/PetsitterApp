package com.example.Petsitter_App.service;


import com.example.Petsitter_App.model.User;
import com.example.Petsitter_App.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getAuth(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = getByLogin(auth.getName());
        long user_id = user.getUser_id();
        return userRepository.findUserById(user_id);
    }


    public User getByLogin(String login){ return userRepository.findByLogin(login);}

    public boolean isUserGood(User user){
        return !user.getName().equals("") && !user.getLastname().equals("") && !user.getLogin().equals("") && !user.getPassword().equals("")
                && !user.getEmail().equals("");
    }

    public boolean isLoginFree(User user){
        List<User> users = getUsers();
        for(User x:users) {
            if (x.getEmail().equals(user.getEmail()) || x.getLogin().equals(user.getLogin())) {
                return false;
            }
        }
        return true;
    }

    public boolean isLongEnough(User user){
        return user.getLogin().length() >= 4 && user.getPassword().length() >= 4;
    }

    public String changePassword(User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        String cur_pass = user.getPassword();
        String new_pass = user.getNew_password();
        String new_pass2 = user.getConfirm_password();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(encoder.matches(cur_pass, userRepository.findByLogin(login).getPassword())){
            if (new_pass.equals(new_pass2)) {
                userRepository.changePassword(login, encoder.encode(new_pass));
                return "Password changed";
            } else {
                return "Different passwords given!";
            }
        }
        else {
            return "Wrong current password!";
        }
    }
}
