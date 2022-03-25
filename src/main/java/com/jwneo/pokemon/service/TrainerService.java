package com.jwneo.pokemon.service;

import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Transactional
    public void saveTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    @Transactional
    public void updateTrainer(String trainerId, String password, String name, String region) {
        Optional<Trainer> findTrainer = trainerRepository.findByLogId(trainerId);

        if (!findTrainer.isEmpty()) {
            //tbd
        }
    }

    @Transactional
    public void updatePokeList(String trainerId, String pokeList) {
        Optional<Trainer> findTrainer = trainerRepository.findByLogId(trainerId);

        if (!findTrainer.isEmpty()) {
            findTrainer.get().updatePokeList(pokeList);
        }
    }


    public Optional<Trainer> findOne(String trainerId) {
        return trainerRepository.findByLogId(trainerId);
    }
}
