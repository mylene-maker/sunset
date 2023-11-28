package com.humanbooster.sunset.services;


import com.humanbooster.sunset.models.Role;
import com.humanbooster.sunset.models.User;
import com.humanbooster.sunset.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpringAuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user = this.userRepository.findByEmail(email);

        if (user == null){
            throw new UsernameNotFoundException("Mail ou mot de passe incorrecte !");
        }

        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

        for (Role role : user.getRoles()){
            GrantedAuthority ga = new SimpleGrantedAuthority(role.getRoleName());
            roles.add(ga);

        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
    }
}
