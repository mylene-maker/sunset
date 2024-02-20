package com.humanbooster.sunset.controllers;

import com.humanbooster.sunset.forms.EmplacementForm;
import com.humanbooster.sunset.forms.ReservationForm;
import com.humanbooster.sunset.models.Reservation;
import com.humanbooster.sunset.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    Validator validator;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView addReservation(){
        ModelAndView mv = new ModelAndView("reservations/form");

        ReservationForm rf = new ReservationForm();
        mv.addObject("reservationForm", rf);

        return mv;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addReservation(ReservationForm reservationForm,
                                 @RequestBody String postPayload, Model model){

        // J'appel mon service de reservation pour lui demander de transformer le body
        // de ma requête HTTP en objet en list d'emplacement
        List<EmplacementForm> listEmplacementForm = this.reservationService.payloadToResas(postPayload);

        // La liste d'emplacement réccupérée depuis mon service je l'ajoute à l'objet commande de mon formulaire
        reservationForm.setEmplacements(listEmplacementForm);

        // Permet de revalider notre champs reservation Form
        // Nous l'avons modifié donc il faut revalider.
        DataBinder binder = new DataBinder(reservationForm);

        // Etant donné que j'ai modifié l'objet que représente mon formulaire,
        // Je demande à la validation spring de revalider mes données
        // Si jamais les emplacements que j'ai ajouté ne sont pas valides
        // Les erreurs seront affiché dans mon formulaire
        binder.setValidator(validator);
        // Je demande de revalider mon formulaire avec le nouvel objet reservationForm
        binder.validate(reservationForm);
        // Je réccupére les nouvelles erreurs
        BindingResult bindingResult = binder.getBindingResult();

        if (bindingResult.hasErrors()) {
            model.addAttribute("org.springframework.validation.BindingResult.reservationForm", bindingResult);
            // On ajoute notre objet de reservation
            model.addAttribute("reservationForm", reservationForm);

            return "reservations/form";
        } else {
            // Envoyer les données en BDD
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();

            this.reservationService.persistReservationFromForm(reservationForm, currentPrincipalName);

            return "reservations/success-form";
        }


    }





}
