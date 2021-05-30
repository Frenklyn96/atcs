package com.example.acts.repository;

import com.example.acts.entity.Posizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PosizioneRepository extends JpaRepository<Posizione,Long> {
    @Query(value = "SELECT gruppo_id,ora_fine,ora_inizio FROM gruppo_visitatori as a,posizione as b WHERE a.visitatori_id=b.visitatore_id")
    List<Posizione> findByidVisitatoreAndidStanza();
}
