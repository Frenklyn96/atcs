package com.example.acts.services;

import com.example.acts.entity.*;
import com.example.acts.repository.PosizioneRepository;
import com.example.acts.repository.PresentazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PresentazioneServices {
    @Autowired
    private PresentazioneRepository presentazioneRepository;

    public Boolean addElem (Stanza stanza, Date oraInizio, Date oraFine, Visitatore visitatore,int voto, String interruzione){
        try {
            presentazioneRepository.save(new Presentazione(stanza,oraInizio,oraFine,visitatore,voto,interruzione));
        } catch (Exception e)
        {
            System.out.println("Error PresentazioneServices "+e);
            return false;
        }
        return true;
    }


    /*public List<Posizione> getPosizioniByGruppo(Long idGruppo) {
        return(presentazioneRepository.findByidGruppo(idGruppo));
    }*/
}
