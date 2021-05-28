package com.example.acts.repository;

import com.example.acts.entity.Gruppo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GruppoRepository extends JpaRepository<Gruppo,Long> {
    Optional<Gruppo> findById(Long l);
}
