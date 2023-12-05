package com.humanbooster.sunset.controllers;

import com.humanbooster.sunset.models.Command;
import com.humanbooster.sunset.models.Reservation;
import com.humanbooster.sunset.services.CommandService;
import com.humanbooster.sunset.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        List<Reservation> reservations = reservationService.findAll();
        model.addAttribute("commands", commands);
        model.addAttribute("reservations", reservations);
        ModelAndView mv = new ModelAndView("admin");

        return mv;
    }

    @RequestMapping("/accept/{reservationId}")
    public String acceptReservation(@PathVariable Long reservationId) {
        // Appeler le service pour accepter la réservation
        reservationService.acceptReservation(reservationId);
        // Rediriger vers la page d'administration ou une autre page appropriée
        return "redirect:/admin";
    }

    @RequestMapping("/reject/{reservationId}")
    public String rejectReservation(@PathVariable Long reservationId) {

        reservationService.rejectReservation(reservationId);

        return "redirect:/admin";
    }
}
