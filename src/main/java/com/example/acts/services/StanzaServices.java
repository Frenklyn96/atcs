package com.example.acts.services;

import com.example.acts.entity.Stanza;
import com.example.acts.repository.StanzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StanzaServices {
    @Autowired
    private StanzaRepository stanzaRepository;

    public boolean addElem (String nome) {
        try {
        stanzaRepository.save(new Stanza(nome));
        }catch (Exception e) {
            System.out.println("Error in StanzaServices: "+e);
            return false;
        }
        return true;
    }

    public Stanza getStanza(String stanza) {
        return(stanzaRepository.findByNome(stanza));
    }

    public boolean EsisteStanza(String s) {
        return (stanzaRepository.findByNome(s)!=null);
    }

    public void save(Stanza s) {
        stanzaRepository.save(s);
    }
}
