package com.humanbooster.sunset.services;

import com.humanbooster.sunset.models.User;
import com.humanbooster.sunset.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public User save(User user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        if (Objects.equals(user.getRoles(), this.roleService.findByRoleName("USER"))) {
            user.addRole(this.roleService.findByRoleName("USER"));
        } else if (Objects.equals(user.getRoles(), this.roleService.findByRoleName("ADMIN"))) {
            user.addRole(this.roleService.findByRoleName("ADMIN"));
        }else {
            user.addRole(this.roleService.findByRoleName("USER"));
            user.addRole(this.roleService.findByRoleName("ADMIN"));
        }
        return this.userRepository.save(user);
    }

    public User saveUser(User user){
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return user;
    }
}
