package com.example.acts.services;

import com.example.acts.entity.*;
import com.example.acts.repository.PosizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PosizioneServices {
    @Autowired
    private PosizioneRepository posizioneRepository;

    public Boolean addElem (Stanza stanza, Date oraInizio, Date oraFine, Visitatore visitatore, Gruppo gruppo){
        try {
            posizioneRepository.save(new Posizione(stanza,oraInizio,oraFine,visitatore,gruppo));
        } catch (Exception e)
        {
            System.out.println("Error PosizioneServices "+e);
            return false;
        }
        return true;
    }

    public List<RisultatoQuery> getByGroupOra(Gruppo gruppo){
        return ( posizioneRepository.findByGruppoOra(gruppo));
    }
    public List<Posizione> getByGroup(Gruppo gruppo){
        return(posizioneRepository.findByGruppo(gruppo));
    }
    public List<RisultatoQuery> getByVisitatoreOra(Visitatore visitatore){
        return(posizioneRepository.findByVisitatoreOra(visitatore));
    }

    public Posizione getLast() {
        return(posizioneRepository.findTopByOrderByIdDesc());

    }

    public List<RisultatoQuery> getAll() {
        return(posizioneRepository.trovaRisQuery());
    }
}
