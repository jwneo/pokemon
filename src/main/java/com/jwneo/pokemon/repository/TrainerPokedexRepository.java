package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.model.TrainerPokedex;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainerPokedexRepository extends JpaRepository<TrainerPokedex, Long> {

    @EntityGraph(attributePaths = {"pokedex"})
    @Query(value = "select tp from TrainerPokedex tp where tp.trainer = :trainer")
    List<TrainerPokedex> findByTrainer(@Param("trainer") Trainer trainer);

    @Modifying(clearAutomatically = true)
    @Query(value = "delete from TrainerPokedex tp where tp.trainer = :trainer")
    void deleteByTrainer(@Param("trainer") Trainer trainer);
}
