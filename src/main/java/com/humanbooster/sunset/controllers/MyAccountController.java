package com.humanbooster.sunset.controllers;

import com.humanbooster.sunset.models.Reservation;
import com.humanbooster.sunset.models.User;
import com.humanbooster.sunset.services.ReservationService;
import com.humanbooster.sunset.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MyAccountController {

    @Autowired
    private UserService userService;

    @Autowired
    ReservationService reservationService;

    @RequestMapping("/myaccount")
    public ModelAndView myAccount(Model model) {

        // Récupérez l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User currentUser = userService.findByEmail(email);

        List<Reservation> reservations = reservationService.findAll();

        // Ajoutez le nom d'utilisateur au modèle
        model.addAttribute("user", currentUser);
        model.addAttribute("reservations", reservations);

        ModelAndView mv = new ModelAndView("myaccount");
        return mv;
    }

    @RequestMapping(value = "/edit/{user}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(required = false) User user){
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas d'utilisateur");
        }
        ModelAndView mv = new ModelAndView("myaccount");
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editSubmit(@Valid User user, BindingResult bindingResult){
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas d'utilisateur");
        }

        if (bindingResult.hasErrors()) {
            System.out.println("test");
            return "myaccount";

        }else {
            this.userService.save(user);
            System.out.println(user);
            return "redirect:/myaccount";
        }

    }
}

