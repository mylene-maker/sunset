package com.humanbooster.sunset.repositories;

import com.humanbooster.sunset.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);




}
