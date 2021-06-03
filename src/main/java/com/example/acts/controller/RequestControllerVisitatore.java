package com.example.acts.controller;

import com.example.acts.entity.*;
import com.example.acts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
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

    //todo restituisci json con presentazione e posizione in ordine di data e tempo con relativo tempo in secondi ciascuno
    //per ogni visitatore
    @RequestMapping("/animazione/{id}")
    public String animazione(@PathVariable("id") Long idVisitatore, Model model)
    {
        List<RisultatoQuery> a = posizioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(idVisitatore).get());
        List<RisultatoQuery> b= presentazioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(idVisitatore).get());
        a.addAll(b);
        Collections.sort(a, new Comparator<RisultatoQuery>() {
            @Override
            public int compare(RisultatoQuery o1, RisultatoQuery o2) {
                return o1.getOraInizio().compareTo(o2.getOraInizio());
            }
        });
        model.addAttribute("posizioni",a);
        return("/animazione");
    }



}
