package com.example.acts.services;

import com.example.acts.entity.Gruppo;
import com.example.acts.entity.Stanza;
import com.example.acts.entity.Visitatore;
import com.example.acts.repository.VisitatoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VisitatoreServices {
    @Autowired
    private VisitatoreRepository visitatoreRepository;

    public Boolean addElem(Long id, String nome, String cognome, Date oraInizio, Date oraFine, Boolean headphones, Gruppo gruppo, Stanza stanza){
        try{
            visitatoreRepository.save(new Visitatore(id,nome,cognome,oraInizio,oraFine,headphones,gruppo,stanza));
        }catch (Exception e){
            System.out.println("Error in VisitatoreServices "+e);
            return false;
        }
        return true;
    }


    public Optional<Visitatore> getVisitatoreById(Long l) {
        return visitatoreRepository.findById(l);
    }
}
