package com.example.acts.repository;

import com.example.acts.entity.Stanza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StanzaRepository extends JpaRepository<Stanza, Long> {
    Stanza findByNome (String stanza);

}
