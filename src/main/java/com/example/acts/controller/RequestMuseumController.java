package com.example.acts.controller;

import com.example.acts.entity.RisultatoQuery;
import com.example.acts.entity.Visitatore;
import com.example.acts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class RequestMuseumController {
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

    @GetMapping("/museumData")
    public String statisticheMuseo (Model model){
        List<RisultatoQuery> a = posizioneServices.getAll();
        List<RisultatoQuery> b= presentazioneServices.geALL();
        a.addAll(b);
        Map<Integer,Integer> conta= new HashMap();
        for(RisultatoQuery x: a){
            if (conta.get(Integer.valueOf(x.getOraInizio().getHours()))==null)
                conta.put(Integer.valueOf(x.getOraInizio().getHours()),0);
            conta.put(Integer.valueOf(x.getOraInizio().getHours()),conta.get(Integer.valueOf(x.getOraInizio().getHours()))+1);
        }

        Map <String,Integer> contaVisitatoriPerOra= new HashMap<String,Integer>();
        for(RisultatoQuery x: a){
            if (contaVisitatoriPerOra.get(String.valueOf (Integer.valueOf(x.getOraInizio().getHours())+" "+x.getStanza().getId().intValue()))==null)
                contaVisitatoriPerOra.put(String.valueOf (Integer.valueOf(x.getOraInizio().getHours())+" "+x.getStanza().getId().intValue()),0);
            contaVisitatoriPerOra.put(String.valueOf (Integer.valueOf(x.getOraInizio().getHours())+" "+x.getStanza().getId().intValue()),contaVisitatoriPerOra.get(String.valueOf (Integer.valueOf(x.getOraInizio().getHours())+" "+x.getStanza().getId().intValue())+1));
        }

        model.addAttribute("conta",conta);
        model.addAttribute("contaVisitatoriPerOra",contaVisitatoriPerOra);

        return("museumData");
    }
}