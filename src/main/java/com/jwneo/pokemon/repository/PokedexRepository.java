package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Pokedex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PokedexRepository extends JpaRepository<Pokedex, Long> {

    List<Pokedex> findByCode(String code);

    @Query(value = "select p from Pokedex p where instr(:pokelist, p.name) > 0")
    List<Pokedex> findByPokeList(@Param("pokelist") String pokeList);
}
