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
        Optional<Trainer> findTrainer = trainerRepository.findById(trainerId);

        if (!findTrainer.isEmpty()) {
            //tbd
        }
    }

    public Optional<Trainer> findOne(String trainerId) {
        return trainerRepository.findById(trainerId);
    }
}
