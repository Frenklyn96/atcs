package com.example.acts.controller;

import com.example.acts.entity.Posizione;
import com.example.acts.services.GruppoServices;
import com.example.acts.services.PosizioneServices;
import com.example.acts.services.StanzaServices;
import com.example.acts.services.VisitatoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;

@Controller
public class ProvaController {
    @Autowired
    private GruppoServices gruppoServices;
    @Autowired
    private PosizioneServices posizioneServices;
    @Autowired
    private StanzaServices stanzaServices;
    @Autowired
    private VisitatoreServices visitatoreServices;


    @GetMapping("/ciao")
    public String sayHello (@RequestParam(value="name",defaultValue ="world") String name){
        return String.format("Hello %s", name);
    }

    @GetMapping ("/inizializza")
    public void inizializza (){
        stanzaServices.addElem(0L,"cameralollo");
        gruppoServices.addElem(0L,new Date(10,12,2020),new Date(10,12,2020,05,06),new Date(10,12,2020,06,06),true,stanzaServices.getStanza("cameralollo"));
        System.out.println("-->"+gruppoServices.getGruppo(0L).get().getId());
        visitatoreServices.addElem(0L,"lorenzo","Cancello",new Date(10,12,2020,05,06),new Date(10,12,2020,06,06),true,gruppoServices.getGruppo(0L).get(),stanzaServices.getStanza("cameralollo"));
        posizioneServices.addElem(stanzaServices.getStanza("cameralollo"),new Date(19,12,2020,05,06),new Date(19,12,2020,06,06),visitatoreServices.getVisitatoreById(0L).get());

    }
}
