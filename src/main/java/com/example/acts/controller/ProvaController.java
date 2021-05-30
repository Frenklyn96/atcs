package com.example.acts.controller;

import com.example.acts.entity.Gruppo;
import com.example.acts.entity.Posizione;
import com.example.acts.entity.Stanza;
import com.example.acts.entity.Visitatore;
import com.example.acts.services.*;
import au.com.bytecode.opencsv.CSVReader;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class ProvaController {
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


    @GetMapping("/ciao")
    public String sayHello (@RequestParam(value="name",defaultValue ="world") String name){
        return String.format("Hello %s", name);
    }

    @GetMapping ("/inizializza")
    public void inizializza (/*Long idStanza, String nomeStanza,Long idGruppo, Date dataGruppo, Date oraInizioGruppo, Date oraFineGruppo, Boolean headphonesGruppo,
                             Long idVisitatore, String nomeVisitatore, String cognomeVisitatore, Date oraInizioVisitatore, Date oraFineVisitatore, Boolean headphonesVisitatore,
                             Date dataInizioPosizione,Date daFinePosizione*/){
        stanzaServices.addElem("cameralollo");
        gruppoServices.addElem(0L,new Date(10,12,2020),new Date(10,12,2020,05,06),new Date(10,12,2020,06,06),true);
        aggiungiStanzaGruppo(0L,"cameralollo");

        visitatoreServices.addElem(0L,"lorenzo","Cancello",true,gruppoServices.getGruppo(0L).get());
        posizioneServices.addElem(stanzaServices.getStanza("cameralollo"),new Date(19,12,2020,05,06),new Date(19,12,2020,06,06),visitatoreServices.getVisitatoreById(0L).get());

    }

    public void aggiungiStanzaGruppo(Long idGruppo, String nomeStanza) {
        Gruppo g= gruppoServices.getGruppo(idGruppo).get();
        Stanza s= stanzaServices.getStanza(nomeStanza);
        g.addStanza(s);
        gruppoServices.save(g);
        s.addGruppo(g);
        stanzaServices.save(s);
    }

    public void aggiungiStanzaVisitatore(String nomeStanza, Long idVisitaotre) {
        Visitatore v= visitatoreServices.getVisitatoreById(idVisitaotre).get();
        Stanza s= stanzaServices.getStanza(nomeStanza);
        v.addStanza(s);
        visitatoreServices.save(v);
        s.addVisitatore(v);
        stanzaServices.save(s);
    }

    @GetMapping("/grouping")
    public void LetturaRecordGruppi() throws IOException, ParseException {
        String path="C:\\Users\\marco\\Downloads\\02_Seminario\\02_Seminario\\Museum Data\\";
        File folder = new File("C:\\Users\\marco\\Downloads\\02_Seminario\\02_Seminario\\Museum Data");
        File[] listOfFiles = folder.listFiles();
        for (File file:listOfFiles){
            if (file.getName().equals("Visitors grouping.csv"))
            {
                System.out.println("Inizio lettura file");
                System.out.println(file.getName());
                CSVReader reader = new CSVReader(new FileReader(path+file.getName()),';', '"', 1);
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                       riempiGruppo(reader);
                }
            }
        }

    }

    private void riempiGruppo(CSVReader reader) throws IOException, ParseException {
        String[] nextLine;
        nextLine = reader.readNext();
        Boolean headphones;
        while (nextLine!=null) {
            headphones=false;
            if (!gruppoServices.esisteGruppo(Long.parseLong(nextLine[1])))
            {
                if (nextLine[7].equals("Y"))
                    headphones=true;
                gruppoServices.addElem(Long.parseLong(nextLine[1]),creaData(nextLine[3]),creaData(nextLine[4]),creaData(nextLine[5]),headphones);
            }
        nextLine = reader.readNext();
        }

    }

    @GetMapping("/visitorslogs")
    public void LetturaRecaordVisitatori () throws IOException, ParseException {
        String path="C:\\Users\\marco\\Downloads\\02_Seminario\\02_Seminario\\Museum Data\\Visitors Logs\\";
        File folder = new File("C:\\Users\\marco\\Downloads\\02_Seminario\\02_Seminario\\Museum Data\\Visitors Logs");
        File[] listOfFiles = folder.listFiles();
        gruppoServices.addElem(0L,new Date(10,12,2020),new Date(10,12,2020,05,06),new Date(10,12,2020,06,06),true);
        for (File file:listOfFiles){
            if (file.getName().contains(".csv"))
            {
                System.out.println("Inizio lettura file: "+file.getName());
                List<String> array =     Arrays.asList(file.getName().split("_"));
                Long idGruppo = Long.parseLong(array.get(2).substring(0,array.get(2).indexOf(".")));
                Long idVisitatore=Long.parseLong(array.get(1));

                creaVisitaotre(idGruppo,idVisitatore);
                CSVReader reader = new CSVReader(new FileReader(path+file.getName()),',', '"', 1);
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {

                    if (nextLine[0].equals("Positions "))
                        RiempiPosizioni(reader,idVisitatore,idGruppo);
                    else if (nextLine[0].equals("presentations "))
                        RiempiPresentatione(reader,idVisitatore,idGruppo);
                }

            }
        }
    }

    //restituisce id visitatore
    private void creaVisitaotre(Long idGruppo,Long idVisitatore) {

        if (!visitatoreServices.esisteVisitatore(idVisitatore)){
            visitatoreServices.addElem(idVisitatore, "Lorenzo", "Cancello", false, gruppoServices.getGruppo(idGruppo).get());
            aggiungiGruppoVisitatore (idGruppo,idVisitatore);

        }
    }


    public void aggiungiGruppoVisitatore(Long idGruppo, Long idVisitatore){
        Gruppo g= gruppoServices.getGruppo(idGruppo).get();
        Visitatore v= visitatoreServices.getVisitatoreById(idVisitatore).get();
        g.addVisitatore(v);
        gruppoServices.save(g);
        v.addGruppo(g);
        visitatoreServices.save(v);

    }

    public void RiempiPosizioni(CSVReader reader,Long idVisitatore,Long idGruppo) throws IOException, ParseException {
        String[] nextLine;
        nextLine = reader.readNext();
        while ((nextLine[0].length()!=0)&&(nextLine!=null)) {
                if (!stanzaServices.EsisteStanza(nextLine[2]))
                {
                    stanzaServices.addElem(nextLine[2]);
                    aggiungiStanzaVisitatore(nextLine[2],idVisitatore);
                    aggiungiStanzaGruppo(idGruppo,nextLine[2]);
                }
                posizioneServices.addElem(stanzaServices.getStanza(nextLine[2]), creaData(nextLine[0]), creaData(nextLine[1]),visitatoreServices.getVisitatoreById(idVisitatore).get());
                nextLine = reader.readNext();
        }
    }

    public void RiempiPresentatione(CSVReader reader,Long idVisitatore,Long idGruppo) throws IOException, ParseException {
        String[] nextLine;
        nextLine = reader.readNext();
        while ((nextLine[0].length()!=0)&&(nextLine!=null)) {
                System.out.println(nextLine.length );
                if (!stanzaServices.EsisteStanza(nextLine[2]))
                 {
                     stanzaServices.addElem(nextLine[2]);
                     aggiungiStanzaVisitatore(nextLine[2],idVisitatore);
                     aggiungiStanzaGruppo(idGruppo,nextLine[2]);


                 }
                presentazioneServices.addElem(stanzaServices.getStanza(nextLine[2]), creaData(nextLine[0]), creaData(nextLine[1]),visitatoreServices.getVisitatoreById(idVisitatore).get());
                nextLine = reader.readNext();
        }
    }
    public Date creaData (String data) throws ParseException {
        Date date1;
        if(data.contains("/"))
            date1=new SimpleDateFormat("dd/MM/yyyy").parse(data);
        else
            date1=new SimpleDateFormat("HH:mm").parse(data);
        return (date1);
    }

}
