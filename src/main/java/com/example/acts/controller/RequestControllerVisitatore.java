package com.example.acts.controller;

import com.example.acts.entity.Gruppo;
import com.example.acts.entity.Posizione;
import com.example.acts.entity.Visitatore;
import com.example.acts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class RequestControllerVisitatore {
    @Autowired
    private PresentazioneServices presentazioneServices;
    @Autowired
    private GruppoServices gruppoServices;
    @Autowired
    private PosizioneServices posizioneServices;
    @Autowired
    private StanzaServices stanzaServices;
    @Autowired
    private VisitatoreServices visitatoreServices;

    @RequestMapping("/visitorSummuary/{id}")
    public String prova(@PathVariable("id")Long idVisitatore, Model model)
    {

        model.addAttribute("posizioni",posizioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(idVisitatore).get()));
        model.addAttribute("presentazioni",presentazioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(idVisitatore).get()));
        return "visitedroombygroup";
    }



}
