package com.humanbooster.sunset.repositories;

import com.humanbooster.sunset.models.Command;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandRepository extends CrudRepository<Command, Long> {

}
