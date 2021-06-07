package com.example.acts.controller;

import com.example.acts.entity.*;
import com.example.acts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
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

    //Restituisce tutti i visitatori nel db
    @RequestMapping(value = "/lista" )
    public String tuttiVisitatoriId(Model model){
        model.addAttribute("visitatori",visitatoreServices.getAllVisitatori());
        return("lista");
    }

    //Ritorno Summuary visitatori
    @RequestMapping(value = "/visitorSummuary/{id}")
    public String prova(@PathVariable("id")Long idVisitatore, Model model)
    {   Double avgTempo,avgVoto;
        List<Presentazione> temp;
        List<String>enjoy=new ArrayList<>();
        model.addAttribute("posizioni",posizioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(idVisitatore).get()));
        List<RisultatoQuery> p=presentazioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(idVisitatore).get());
        for (RisultatoQuery t:p){
            temp=presentazioneServices.findByName(t.getPresentazione());
        avgTempo=temp.stream().mapToDouble((x)->Double.valueOf(x.getTempoTotale())).average().getAsDouble();
        avgVoto= temp.stream().mapToDouble((x)->Double.valueOf(x.getVoto())).average().getAsDouble();
        String risp;
        if(t.getTempoTotale()>avgTempo)
            risp="S";
        else
            risp="N";
        if (t.getVoto()>avgVoto)
            risp=risp+"S";
        else
            risp=risp+"N";
        if(t.getInterruzione().equals("system"))
            risp=risp+"S";
        else
            risp=risp+"N";
        enjoy.add(risp);
        }
        model.addAttribute("presentazione",presentazioneServices);
        model.addAttribute("enjoy",enjoy);
        return "visitedroombygroup";
    }

    //todo restituisci json con presentazione e posizione in ordine di data e tempo con relativo tempo in secondi ciascuno
    //per ogni visitatore
    @RequestMapping("/playback/{id}")
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
        return "/playback";
    }



}
