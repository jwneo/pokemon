package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Pokedex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokedexRepository extends JpaRepository<Pokedex, Long> {

    List<Pokedex> findByCode(String code);
}
