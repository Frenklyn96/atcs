package com.example.acts.repository;

import com.example.acts.entity.Gruppo;
import com.example.acts.entity.Posizione;
import com.example.acts.entity.RisultatoQuery;
import com.example.acts.entity.Visitatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PosizioneRepository extends JpaRepository<Posizione,Long> {


    @Query("SELECT new com.example.acts.entity.RisultatoQuery(p.id, min(p.oraInizio), max(p.oraFine) , p.gruppo, p.stanza) " +
            "FROM Posizione AS p WHERE p.gruppo=:gruppo GROUP BY p.stanza ")
     List <RisultatoQuery> findByGruppoOra(@Param ("gruppo") Gruppo gruppo);

    List<Posizione> findByGruppo(Gruppo gruppo);

    @Query("SELECT new com.example.acts.entity.RisultatoQuery(p.stanza.id, min(p.oraInizio), max(p.oraFine),p.gruppo,p.stanza)" +
            "FROM Posizione AS p WHERE p.visitatore=:visitatore GROUP BY p.stanza")
    List<RisultatoQuery> findByVisitatoreOra(@Param("visitatore")Visitatore visitatore);

    Posizione findTopByOrderByIdDesc();

    @Query("SELECT new com.example.acts.entity.RisultatoQuery(p.stanza.id, p.oraInizio, p.oraFine,p.gruppo,p.stanza) " +
            "FROM Posizione AS p")
    List<RisultatoQuery> trovaRisQuery();
}
