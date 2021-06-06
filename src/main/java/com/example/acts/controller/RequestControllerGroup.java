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
public class RequestControllerGroup {
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
//Ritorna summary dei gruppi
    @RequestMapping("/groupSummuary/{id}")
    public String prova(@PathVariable("id")Long idGruppo, Model model)
    {
        model.addAttribute("posizioni",posizioneServices.getByGroupOra(gruppoServices.getGruppo(idGruppo).get()));
        return "visitedroombygroup";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "visitedroombygroup";
    }


}
