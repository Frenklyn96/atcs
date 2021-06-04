package com.example.acts.repository;

import com.example.acts.entity.Visitatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VisitatoreRepository extends JpaRepository<Visitatore,Long> {
    @Override
    Optional<Visitatore> findById(Long aLong);

    @Query("SELECT new com.example.acts.entity.Visitatore(v.id, v.nome, v.cognome, v.headphones, v.gruppo) FROM Visitatore  as v")
    List<Visitatore> findByVisitatore();

}
