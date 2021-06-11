package com.example.acts.controller;

import com.example.acts.entity.RisultatoQuery;
import com.example.acts.entity.Posizione;
import com.example.acts.entity.Presentazione;
import com.example.acts.entity.Visitatore;
import com.example.acts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;

import javassist.expr.NewArray;

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

    // Ritorno Summuary Museo
    @GetMapping("/visitedroombygroup")
    public String statisticheMuseo(Model model) {
        List<RisultatoQuery> a = posizioneServices.getAll();
        List<RisultatoQuery> b = presentazioneServices.geALL();
        a.addAll(b);
        Map<Integer, Integer> conta = new TreeMap<Integer, Integer>();
        for (RisultatoQuery x : a) {
            if (conta.get(Integer.valueOf(x.getOraInizio().getHours())) == null)
                conta.put(Integer.valueOf(x.getOraInizio().getHours()), 0);
            conta.put(Integer.valueOf(x.getOraInizio().getHours()),
                    conta.get(Integer.valueOf(x.getOraInizio().getHours())) + 1);
        }

        Map<String, Integer> contaVisitatoriPerOra = new HashMap<String, Integer>();
        for (RisultatoQuery x : a) {
            if (x.getStanza() != null) {
                String key = Integer.valueOf(x.getOraInizio().getHours()) + " " + x.getStanza().getId().intValue();
                if (contaVisitatoriPerOra.get(key) == null)
                    contaVisitatoriPerOra.put((key), 0);
                contaVisitatoriPerOra.put(key, contaVisitatoriPerOra.get(key) + 1);
            }

            model.addAttribute("conta", conta);
            model.addAttribute("contaVisitatoriPerOra", contaVisitatoriPerOra);
        }

        Map<String, Integer> idList = new TreeMap<String, Integer>();
        for (RisultatoQuery x : a) {
            if (x.getStanza() != null) {
                idList.put(x.getStanza().getNome(), 1);
            }
        }

        Set lista = idList.keySet();
        model.addAttribute("idList", lista);

        return ("visitedroombygroup");
    }

    // Ritorno Summuary Museo
    @GetMapping("/visitedroombygroup_response")
    public String statisticheMuseo(@RequestParam("room")String roomName, Model model) {
        model.addAttribute("nomeStanza", roomName);
        List<RisultatoQuery> a = posizioneServices.getAll();
        List<RisultatoQuery> b = presentazioneServices.geALL();
        a.addAll(b);

        Map<Integer, Integer> contaVisitatoriPerOra = new TreeMap<Integer, Integer>();
        for (RisultatoQuery x : a) {
            if (x.getStanza() != null) {
                Integer key = x.getOraInizio().getHours();
                if (contaVisitatoriPerOra.get(key) == null)
                    contaVisitatoriPerOra.put((key), 0);
                if (x.getStanza().getNome().equals(roomName)) {
                    contaVisitatoriPerOra.put(key, contaVisitatoriPerOra.get(key) + 1);
                }
            }
            model.addAttribute("contaVisitatoriPerOra", contaVisitatoriPerOra);
        }

        return ("visitedroombygroup_response");
    }

    @GetMapping("/roompreferences")
    public String statisticheStanza(Model model) {
        List<RisultatoQuery> posizioniTemp;
        List<RisultatoQuery> a;
        List<RisultatoQuery> b;

        List<Presentazione> presentazioniTemp;

        List<Visitatore> visitatori;

        List<Integer> dati;

        Map<String, List<Integer>> posizioni;

        Posizione posizioneTemp;

        String nomeStanza;
        String nomePresentazione;

        Integer tempoTotale;

        a = posizioneServices.getAll();
        b = presentazioneServices.geALL();
        a.addAll(b);

        posizioni = new TreeMap<String, List<Integer>>();
        
        visitatori = visitatoreServices.getAllVisitatori();

        for (Visitatore v: visitatori) {
            posizioniTemp =  posizioneServices.getByVisitatoreOra(visitatoreServices.getVisitatoreById(v.getId()).get());   // prendo l'elenco delle stanze che un visitatore ha visitato
            
            for (RisultatoQuery p: posizioniTemp) {
                dati = new ArrayList<Integer>();

                tempoTotale = p.getTempoTotale();

                if(p.getStanza()!=null) {
                    nomeStanza = p.getStanza().getNome();
                }
                else {
                    nomeStanza = p.getPresentazione();
                }

                if (posizioni.get(nomeStanza) == null) {
                    dati.add(tempoTotale);
                    dati.add(1);
                    dati.add(tempoTotale);

                    posizioni.put(nomeStanza, dati);
                }
                else {
                    dati.add(0, posizioni.get(nomeStanza).get(0)+tempoTotale);
                    dati.add(1, posizioni.get(nomeStanza).get(1)+1);
                    dati.add(2, (dati.get(0)/dati.get(1)));

                    posizioni.put(nomeStanza, dati);
                }
             }
        }
        model.addAttribute("posizioni", posizioni);

        return ("roompreferences");
    }


    @GetMapping("/index")
    public String home(Model model) {
        return ("index");
    }

    @GetMapping("/assignment")
    public String assignment(Model model) {
        return ("assignment");
    }

}
