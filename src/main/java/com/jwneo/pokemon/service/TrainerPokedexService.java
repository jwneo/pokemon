package com.jwneo.pokemon.service;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.model.TrainerPokedex;
import com.jwneo.pokemon.repository.TrainerPokedexRepository;
import com.jwneo.pokemon.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrainerPokedexService {

    private final TrainerRepository trainerRepository;
    private final TrainerPokedexRepository trainerPokedexRepository;

    @Transactional
    public void saveAll(String trainerId, Collection<Pokedex> pokedexes) {
        Trainer trainer = trainerRepository.findById(trainerId).get();

        trainerPokedexRepository.deleteByTrainer(trainer);

        List<TrainerPokedex> trainerPokedexes = pokedexes.stream()
                .map(pokedex -> new TrainerPokedex(trainer, pokedex))
                .collect(Collectors.toList());

        trainerPokedexRepository.saveAll(trainerPokedexes);
    }

    public List<Pokedex> findAll(String trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId).get();

        return trainerPokedexRepository.findByTrainer(trainer).stream()
                .map(TrainerPokedex::getPokedex)
                .collect(Collectors.toList());
    }
}