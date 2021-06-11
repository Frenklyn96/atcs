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
    @RequestMapping(value = "/playback" )
    public String tuttiVisitatoriId(Model model){
        model.addAttribute("visitatori",visitatoreServices.getAllVisitatori());
        return("playback");
    }

    @RequestMapping(value="/visitorsummuary")
    public String indice (Model model){
        model.addAttribute("visitatori",visitatoreServices.getAllVisitatori());
        return ("visitorsummuary");
    }
    
    //Ritorno Summuary visitatori
    @RequestMapping(value = "/visitorsummuary_response")
    public String prova(@RequestParam("visitor")Long idVisitatore, Model model)
    {   Double avgTempo,avgVoto;
        List<Presentazione> temp;

        //List<List<String>>enjoy=new ArrayList<List<String>>();
        Map<String, List<String>> enjoy = new HashMap<String, List<String>>(); 

        model.addAttribute("posizioni",posizioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(idVisitatore).get()));
        List<RisultatoQuery> p=presentazioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(idVisitatore).get());
        
        for (RisultatoQuery t:p){
            temp = presentazioneServices.findByName(t.getPresentazione());

            avgTempo = temp.stream().mapToDouble((x)->Double.valueOf(x.getTempoTotale())).average().getAsDouble();
            avgVoto = temp.stream().mapToDouble((x)->Double.valueOf(x.getVoto())).average().getAsDouble();

            List<String> risp = new ArrayList<String>();

            if(t.getTempoTotale()>avgTempo) // Stayed more than the average group
                risp.add("Yes");
            else
                risp.add("No");

            if (t.getVoto()>avgVoto)    // Rated presentation higher than the average
                risp.add("Yes");
            else
                risp.add("No");

            if(t.getInterruzione().equals("system"))    // Did not interrupt the presentations
                risp.add("No");
            else
                risp.add("Yes");

            // enjoy.add(risp);
            enjoy.put(t.getPresentazione(), risp);
        }
        model.addAttribute("presentazioni",p);
        model.addAttribute("enjoy",enjoy);
        return "visitorsummuary_response";
    }

    //todo restituisci json con presentazione e posizione in ordine di data e tempo con relativo tempo in secondi ciascuno
    //per ogni visitatore
    @RequestMapping(value="/playback_response",method=RequestMethod.GET)
    public String animazione(@RequestParam("visitor") Long idVisitatore, Model model)
    {
        /*List<RisultatoQuery> a = posizioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(idVisitatore).get());
        List<RisultatoQuery> b= presentazioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(idVisitatore).get());
        a.addAll(b);
        Collections.sort(a, new Comparator<RisultatoQuery>() {
            @Override
            public int compare(RisultatoQuery o1, RisultatoQuery o2) {
                return o1.getOraInizio().compareTo(o2.getOraInizio());
            }
        });
        List<Mappa> posizioni =new ArrayList<Mappa>();
        for(RisultatoQuery x:a){
            if(x.getStanza()!=null)
             posizioni.add(new Mappa(x.getStanza().getId(),x.getTempoTotale()));
            else
                posizioni.add(new Mappa(presentazioneServices.findByName(x.getPresentazione()).get(0).getId(),x.getTempoTotale()));
        }*/
        return ("playback_response");
    }



}
