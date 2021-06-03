package com.example.acts.repository;

import com.example.acts.entity.Posizione;
import com.example.acts.entity.Presentazione;
import com.example.acts.entity.Visitatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PresentazioneRepository extends JpaRepository<Presentazione,Long> {

    @Query("SELECT p.i FROM Presentazione AS p WHERE p.visitatore=:visitatore")
    List<Presentazione> findByVisitatoreOra(@Param("visitatore") Visitatore visitatore);

}
