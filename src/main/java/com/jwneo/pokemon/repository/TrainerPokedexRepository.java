package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.TrainerPokedex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerPokedexRepository extends JpaRepository<TrainerPokedex, Long> {
}
