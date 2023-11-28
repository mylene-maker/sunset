package com.humanbooster.sunset.services;

import com.humanbooster.sunset.models.User;
import com.humanbooster.sunset.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public User save(User user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }
}
