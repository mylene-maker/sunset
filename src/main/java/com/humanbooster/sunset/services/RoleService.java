package com.humanbooster.sunset.services;

import com.humanbooster.sunset.models.Role;
import com.humanbooster.sunset.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByRoleName(String roleName){ return this.roleRepository.findByRoleName(roleName);}
}
