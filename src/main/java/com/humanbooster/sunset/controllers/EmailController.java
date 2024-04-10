package com.humanbooster.sunset.controllers;

import com.humanbooster.sunset.forms.Email;
import com.humanbooster.sunset.services.CommandService;
import com.humanbooster.sunset.services.EmailService;
import com.humanbooster.sunset.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    CommandService commandService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/new/{reservationId}/{reservationDate}/{userEmail}", method= RequestMethod.GET)
    ModelAndView formEmail(@PathVariable Long reservationId, @PathVariable ("reservationDate") String reservationDateStr, @PathVariable String userEmail){
        ModelAndView mv = new ModelAndView("email/email");

        // Convertir la date de chaîne en objet Date
        Date reservationDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            reservationDate = sdf.parse(reservationDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Formatage de la date (jour-mois-année)
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(reservationDate);

        Email email = new Email();
        email.setEmail(userEmail);
        email.setObject("Refus de la réservation du : " + formattedDate);
        email.setContenu("Bonjour, \nNous sommes au regret de vous informer que nous ne sommes pas en mesure d'accepter votre réservation pour la date du " + formattedDate + ". \nNotre équipe reste à votre entière disposition pour plus d'informations. \n L'équipe Sunset Beach ⛱"
        );
        mv.addObject("email", email);

        return mv;
    }

    @RequestMapping(value = "/new/{reservationId}/{reservationDate}/{userEmail}", method= RequestMethod.POST)
    public String formEmailSubmit(@Valid Email email, BindingResult bindingResult, Model model, @PathVariable Long reservationId){

        if(bindingResult.hasErrors()){
            model.addAttribute("email", email);
            return "email/email";
        }else {
            emailService.sendEmail(email, reservationId);
            return "redirect:/email/success";
        }

    }

    @RequestMapping(value = "/success", method= RequestMethod.GET)
    ModelAndView mailOk(){
        ModelAndView mv = new ModelAndView("email/successEmail");

        return mv;
    }
}
