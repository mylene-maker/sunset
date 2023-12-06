package com.humanbooster.sunset.services;

import com.humanbooster.sunset.models.Command;
import com.humanbooster.sunset.repositories.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandService {
    @Autowired
    CommandRepository commandRepository;

    public List<Command> findAll() {
        return (List<Command>) this.commandRepository.findAll();
    }


}
