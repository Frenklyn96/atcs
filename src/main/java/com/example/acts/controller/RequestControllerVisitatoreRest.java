package com.example.acts.controller;

import com.example.acts.entity.Mappa;
import com.example.acts.entity.RisultatoQuery;
import com.example.acts.services.*;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class RequestControllerVisitatoreRest {
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

    @RequestMapping(value="/playback_responselist",method= RequestMethod.GET)
    public List<Mappa> animazione(@RequestParam("visitor") Long idVisitatore, Model model)
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
        List<Mappa> posizioni =new ArrayList<Mappa>();
        for(RisultatoQuery x:a){
            if(x.getStanza()!=null)
                posizioni.add(new Mappa(x.getStanza().getId(),x.getTempoTotale()));
            else
                posizioni.add(new Mappa(presentazioneServices.findByName(x.getPresentazione()).get(0).getIdPresentazione(),x.getTempoTotale()));
        }
        Long id_temp=posizioni.get(0).getId();
        List<Mappa> risp= new ArrayList<Mappa>();
        Mappa temp;
        for (Mappa x:posizioni)
        {
            if((id_temp==x.getId())&&(risp.size()!=0)) {
                temp = risp.get(risp.size() - 1);
                risp.remove(temp);
                temp.setTime(temp.getTime()+x.getTime());
                risp.add(temp);
            }
            else
                risp.add(x);
                id_temp=x.getId();

        }
        return risp;
    }
}
