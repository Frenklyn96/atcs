package com.example.acts.services;

import com.example.acts.entity.Gruppo;
import com.example.acts.repository.GruppoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GruppoServices {
    @Autowired
    private GruppoRepository gruppoRepository;

    private Boolean AddElem (Date data, Date oraInizio, Boolean headphones){
        try {
            gruppoRepository.save(new Gruppo(data,oraInizio,headphones));
        }catch (Exception e){
            System.out.println("Error in GruppoServices "+e);
            return false;
        }
        return true;
    }
}
