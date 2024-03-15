package com.humanbooster.sunset.controllers;

import com.humanbooster.sunset.models.Command;
import com.humanbooster.sunset.services.CommandService;
import com.humanbooster.sunset.services.PaypalService;
import com.humanbooster.sunset.services.ReservationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/panier")
public class PanierController {
    @Autowired
    PaypalService paypalService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    CommandService commandService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView panier(@RequestParam(name = "total", required = false) BigDecimal totalPrice, @RequestParam(name = "commandId", required = false) BigDecimal commandId, Model model){
        List<Command> commands = commandService.findAll();
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("commandId", commandId);
        model.addAttribute("commands", commands);
        ModelAndView mv = new ModelAndView("panier");
        return mv;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public void proceed(@RequestParam(name = "total", required = true) BigDecimal totalPrice, @RequestParam(name = "commandId", required = false) Long commandId ,HttpServletResponse response) throws IOException{
        System.out.println("Prix total : " + totalPrice);
        response.sendRedirect(
                paypalService.createPayment(totalPrice, commandId).getRedirectUrl()
        );
    }

    @RequestMapping(value = "paypal/capture", method = RequestMethod.GET)
    public ModelAndView capturePayment(@RequestParam("token") String token){
        ModelAndView mv = new ModelAndView("success");
        paypalService.capturePayment(token);
        return mv;
    }

    @RequestMapping(value = "paypal/success", method = RequestMethod.GET)
    public ModelAndView paymentSuccess(){
        ModelAndView mv = new ModelAndView("success");

        return mv;
    }

    @RequestMapping(value = "paypal/error", method = RequestMethod.GET)
    public ModelAndView paymentError(){
        ModelAndView mv = new ModelAndView("error");

        return mv;
    }

    @RequestMapping(value = "paypal/cancel", method = RequestMethod.GET)
    public ModelAndView paymentCancel(){
        ModelAndView mv = new ModelAndView("cancel");

        return mv;
    }


}
