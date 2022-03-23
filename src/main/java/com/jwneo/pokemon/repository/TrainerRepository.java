package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, String> {
}
