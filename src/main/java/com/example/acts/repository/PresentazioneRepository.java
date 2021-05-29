package com.example.acts.repository;

import com.example.acts.entity.Posizione;
import com.example.acts.entity.Presentazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentazioneRepository extends JpaRepository<Presentazione,Long> {
}
