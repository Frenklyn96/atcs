package com.example.acts.services;

import com.example.acts.entity.Gruppo;
import com.example.acts.entity.Visitatore;
import com.example.acts.repository.VisitatoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisitatoreServices {
    @Autowired
    private VisitatoreRepository visitatoreRepository;

    public Boolean addElem(Long id, String nome, String cognome, Boolean headphones, Gruppo gruppo){
        try{
            visitatoreRepository.save(new Visitatore(id,nome,cognome,headphones,gruppo));
        }catch (Exception e){
            System.out.println("Error in VisitatoreServices "+e);
            return false;
        }
        return true;
    }


    public boolean esisteVisitatore(Long s) {
        return visitatoreRepository.findById(s).isPresent();
    }


    public Optional<Visitatore> getVisitatoreById(long l) {
        return(visitatoreRepository.findById(l));
    }

    public void save(Visitatore v) {
        visitatoreRepository.save(v);
    }

}
