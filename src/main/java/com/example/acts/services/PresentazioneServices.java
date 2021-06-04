package com.example.acts.services;

import com.example.acts.entity.*;
import com.example.acts.repository.PresentazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PresentazioneServices {
    @Autowired
    private PresentazioneRepository presentazioneRepository;

    public Boolean addElem (Long id,String nome, Date oraInizio, Date oraFine, Visitatore visitatore,int voto, String interruzione){
        try {
            presentazioneRepository.save(new Presentazione(id,nome,oraInizio,oraFine,visitatore,voto,interruzione));
        } catch (Exception e)
        {
            System.out.println("Error PresentazioneServices "+e);
            return false;
        }
        return true;
    }

    public List<RisultatoQuery> getByVisitatoreOra(Visitatore visitatore) {
        return(presentazioneRepository.findByVisitatoreOra(visitatore));
    }

    public Double mediaTempoTrascorso(Long idPresentazione) {
       return(presentazioneRepository.mediaVoto(idPresentazione));
    }

    public List<Presentazione> findByName(String presentazione){
        return (presentazioneRepository.findByNome(presentazione));
    }
}
