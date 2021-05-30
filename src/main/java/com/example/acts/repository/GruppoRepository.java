package com.example.acts.repository;

import com.example.acts.entity.Gruppo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GruppoRepository extends JpaRepository<Gruppo,Long> {
    Optional<Gruppo> findById(Long l);

    List<Gruppo> findAllById(Long iterable);
}
