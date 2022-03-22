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
@Transactional
@RequiredArgsConstructor
public class TrainerPokedexService {

    private final TrainerRepository trainerRepository;
    private final TrainerPokedexRepository trainerPokedexRepository;

    public void saveAllTrainerPokedex(String logId, Collection<Pokedex> pokedexes) {
        Trainer trainer = trainerRepository.findByLogId(logId).get();

        trainerPokedexRepository.deleteByTrainer(trainer);

        List<TrainerPokedex> trainerPokedexes = pokedexes.stream()
                .map(pokedex -> new TrainerPokedex(trainer, pokedex))
                .collect(Collectors.toList());

        trainerPokedexRepository.saveAll(trainerPokedexes);
    }
}
