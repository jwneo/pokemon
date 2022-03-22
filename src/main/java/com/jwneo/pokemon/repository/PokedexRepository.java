package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Pokedex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokedexRepository extends JpaRepository<Pokedex, Long> {
}
