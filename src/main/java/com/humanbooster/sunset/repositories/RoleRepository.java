package com.humanbooster.sunset.repositories;

import com.humanbooster.sunset.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRoleName(String roleName);
}
