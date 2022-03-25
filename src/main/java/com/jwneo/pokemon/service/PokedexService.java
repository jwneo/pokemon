package com.jwneo.pokemon.service;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.repository.PokedexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PokedexService {

    private final PokedexRepository pokedexRepository;

    public List<Pokedex> findByCode(String code) {
        return pokedexRepository.findByCode(code);
    }

    public List<Pokedex> findByTrainer(Trainer trainer) {
        return pokedexRepository.findByPokeList(trainer.getPokeList());
    }

    public List<Pokedex> findAll() {
        return pokedexRepository.findAll();
    }
}
