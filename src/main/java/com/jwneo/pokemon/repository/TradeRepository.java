package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Trade;
import com.jwneo.pokemon.model.Trainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    List<Trade> findByTrainer(Trainer trainer);

    @EntityGraph(attributePaths = {"trainer"})
    @Override
    Page<Trade> findAll(Pageable pageable);
}
