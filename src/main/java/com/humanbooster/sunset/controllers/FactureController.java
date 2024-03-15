package com.humanbooster.sunset.controllers;

import com.humanbooster.sunset.repositories.FactureRepository;
import com.humanbooster.sunset.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FactureController {
    @Autowired
    PdfService pdfService;

    @Autowired
    FactureRepository factureRepository;


}
