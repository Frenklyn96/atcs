package com.example.acts.repository;

import com.example.acts.entity.Visitatore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitatoreRepository extends JpaRepository<Visitatore,Long> {
    @Override
    Optional<Visitatore> findById(Long aLong);
}
