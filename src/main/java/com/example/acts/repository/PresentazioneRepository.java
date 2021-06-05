package com.example.acts.repository;

import com.example.acts.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresentazioneRepository extends JpaRepository<Presentazione,Long> {

    @Query("SELECT new com.example.acts.entity.RisultatoQuery (p.idPresentazione,min(p.oraInizio),max(p.oraFine),p.nome,p.voto,p.interruzione)" +
            "FROM Presentazione AS p WHERE p.visitatore=:visitatore GROUP BY (p.nome)")
    List<RisultatoQuery> findByVisitatoreOra(@Param("visitatore") Visitatore visitatore);


    @Query("SELECT new java.lang.Double(AVG(p.voto)) FROM Presentazione as p WHERE p.idPresentazione=:idPresentazione")
    Double mediaVoto(@Param("idPresentazione") Long idPresentazione);


    List<Presentazione> findByNome(String presentazione);

    @Query("SELECT new com.example.acts.entity.RisultatoQuery(p.idPresentazione,p.oraInizio,p.oraFine,p.nome,p.voto,p.interruzione) FROM Presentazione AS p")
    List<RisultatoQuery> trovaRisQuery();
}
