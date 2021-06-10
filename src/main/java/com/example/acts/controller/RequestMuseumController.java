package com.example.acts.controller;

import com.example.acts.entity.RisultatoQuery;
import com.example.acts.entity.Visitatore;
import com.example.acts.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/index")
    public String home(Model model) {
        return ("index");
    }

    @GetMapping("/assignment")
    public String assignment(Model model) {
        return ("assignment");
    }

}
