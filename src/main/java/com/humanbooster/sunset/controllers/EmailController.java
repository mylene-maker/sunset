package com.humanbooster.sunset.controllers;

import com.humanbooster.sunset.forms.Email;
import com.humanbooster.sunset.models.Command;
import com.humanbooster.sunset.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;


    @RequestMapping(value = "/new/{reservationId}", method= RequestMethod.GET)
    ModelAndView formEmail(){
        ModelAndView mv = new ModelAndView("email/email");

        mv.addObject("email", new Email());

        return mv;
    }

    @RequestMapping(value = "/new/{reservationId}", method= RequestMethod.POST)
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
