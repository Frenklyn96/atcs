package com.example.acts.services;

import com.example.acts.entity.Gruppo;
import com.example.acts.repository.GruppoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GruppoServices {
    @Autowired
    private GruppoRepository gruppoRepository;

    public Boolean addElem (Long id,Date data, Date oraInizio, Date oraFine, Boolean headphones){
        try {
            gruppoRepository.save(new Gruppo(id,data,oraInizio,oraFine,headphones));
        }catch (Exception e) {
            System.out.println("Error in GruppoServices " + e);
            return false;
        }
        return true;
    }

   // public void setStanza (Stanza stanza){

   // }


    public Optional<Gruppo> getGruppo(Long l) {
       return(gruppoRepository.findById(l));
    }

    public void save (Gruppo gruppo) {
        gruppoRepository.save(gruppo);
    }

    public boolean esisteGruppo(Long l) {
        return(gruppoRepository.findById(l).isPresent());
    }


}
