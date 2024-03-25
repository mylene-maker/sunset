package com.humanbooster.sunset.services;

import com.humanbooster.sunset.models.Command;
import com.humanbooster.sunset.models.CompletedOrder;
import com.humanbooster.sunset.repositories.CommandRepository;
import com.humanbooster.sunset.repositories.CompleteOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandService {
    @Autowired
    CommandRepository commandRepository;

    @Autowired
    CompleteOrderRepository completeOrderRepository;

    public List<Command> findAll() {
        return (List<Command>) this.commandRepository.findAll();
    }

    public Command save(Command command){
        return this.commandRepository.save(command);
    }

    public void isPay(Long commandId, String payId) {
        CompletedOrder completedOrder = completeOrderRepository.findByPayId(payId);
        System.out.println("voici l'id du completedOrder : " + completedOrder);
        Optional<Command> optionnalCommand = commandRepository.findById(commandId);
        if (optionnalCommand.isPresent()) {
            Command command = optionnalCommand.get();
            command.setPayment(true);
            command.setIdPayment(payId);
            commandRepository.save(command);
        }
    }


}
