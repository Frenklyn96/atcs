package com.example.acts.repository;

import com.example.acts.entity.Posizione;
import com.example.acts.entity.Presentazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PresentazioneRepository extends JpaRepository<Presentazione,Long> {

}
