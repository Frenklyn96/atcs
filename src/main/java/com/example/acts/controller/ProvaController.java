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

@RestController
public class ProvaController {
    @Autowired
    GruppoServices gruppoServices;
    @Autowired
    PosizioneServices posizioneServices;
    @Autowired
    StanzaServices stanzaServices;
    @Autowired
    VisitatoreServices visitatoreServices;


    @GetMapping("/ciao")
    public String sayHello (@RequestParam(value="name",defaultValue ="world") String name){
        return String.format("Hello %s", name);
    }

    @GetMapping ("/inizializza")
    public void inizializza (){
        stanzaServices.addElem("cameralollo");
       gruppoServices.addElem(new Date(10,12,2020,05,06),new Time(05,05,05),true);
       posizioneServices.addElem(stanzaServices.getStanza("cameralollo"),new Date(19,12,2020,05,06));
        visitatoreServices.addElem("lorenzo","Cancello",new Date(10,12,2020,05,06),true);
    }
}
