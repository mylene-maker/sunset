package com.humanbooster.sunset.controllers;

import com.humanbooster.sunset.models.Command;
import com.humanbooster.sunset.models.Reservation;
import com.humanbooster.sunset.models.User;
import com.humanbooster.sunset.services.CommandService;
import com.humanbooster.sunset.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    CommandService commandService;

    @RequestMapping()
    ModelAndView adminHome(Model model){

        List<Command> commands = commandService.findAll();
        model.addAttribute("commands", commands);
        ModelAndView mv = new ModelAndView("admin");

        return mv;
    }

    @RequestMapping("/accept/{reservationId}")
    public String acceptReservation(@PathVariable Long reservationId) {
        reservationService.acceptReservation(reservationId);
        return "redirect:/admin";
    }


    @RequestMapping("/edit/{reservationId}/{column}")
    public String editReservation(@PathVariable Long reservationId, @PathVariable int column) {
        reservationService.editReservation(reservationId, column);
        return "redirect:/admin";
    }

}
