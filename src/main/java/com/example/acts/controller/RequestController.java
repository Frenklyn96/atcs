package com.example.acts.controller;

import com.example.acts.entity.Visitatore;
import com.example.acts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class RequestController {
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

    @RequestMapping("/groupSummuary/{id}")
    public String prova(@PathVariable("id")Long idGruppo, Model model)
    {
        model.addAttribute("stanze",gruppoServices.getGruppo(idGruppo).get().getStanze());
        Optional<Visitatore> visitatori = visitatoreServices.getVisitaotoreByGroup(idGruppo);
        presentazioneServices.getTempo (visitatori,gruppoServices.getGruppo(idGruppo).get().getStanze());
        model.addAttribute("tempoStanze",posizioneServices.getOre (gruppoServices.getGruppo(idGruppo).get().getStanze()));
        return "visitedroombygroup";
    }

    @GetMapping("/greeting")

    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "visitedroombygroup";
    }


}
