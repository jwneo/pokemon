package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Optional<Trainer> findByLoginId(String loginId);
}
