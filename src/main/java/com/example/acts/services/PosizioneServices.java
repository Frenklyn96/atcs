package com.example.acts.services;

import com.example.acts.entity.Posizione;
import com.example.acts.entity.Stanza;
import com.example.acts.entity.Visitatore;
import com.example.acts.repository.PosizioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PosizioneServices {
    @Autowired
    private PosizioneRepository posizioneRepository;

    public Boolean addElem (Stanza stanza, Date oraInizio, Date oraFine, Visitatore visitatore){
        try {
            posizioneRepository.save(new Posizione(stanza,oraInizio,oraFine,visitatore));
        } catch (Exception e)
        {
            System.out.println("Error PosizioneServices "+e);
            return false;
        }
        return true;
    }

}
