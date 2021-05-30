package com.example.acts.services;

import com.example.acts.entity.Posizione;
import com.example.acts.entity.Presentazione;
import com.example.acts.entity.Stanza;
import com.example.acts.entity.Visitatore;
import com.example.acts.repository.PosizioneRepository;
import com.example.acts.repository.PresentazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PresentazioneServices {
    @Autowired
    private PresentazioneRepository presentazioneRepository;

    public Boolean addElem (Stanza stanza, Date oraInizio, Date oraFine, Visitatore visitatore){
        try {
            presentazioneRepository.save(new Presentazione(stanza,oraInizio,oraFine,visitatore));
        } catch (Exception e)
        {
            System.out.println("Error PresentazioneServices "+e);
            return false;
        }
        return true;
    }


}
