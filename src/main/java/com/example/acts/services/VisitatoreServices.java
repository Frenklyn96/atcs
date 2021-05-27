package com.example.acts.services;

import com.example.acts.entity.Visitatore;
import com.example.acts.repository.VisitatoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VisitatoreServices {
    @Autowired
    private VisitatoreRepository visitatoreRepository;

    public Boolean addElem(String nome, String cognome, Date oraInizio, Boolean headphones){
        try{
            visitatoreRepository.save(new Visitatore(nome,cognome,oraInizio,headphones));
        }catch (Exception e){
            System.out.println("Error in VisitatoreServices "+e);
            return false;
        }
        return true;
    }


}
