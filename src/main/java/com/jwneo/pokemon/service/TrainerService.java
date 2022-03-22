package com.jwneo.pokemon.service;

import com.jwneo.pokemon.model.Address;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Transactional
    public void saveTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }
    
    @Transactional
    public void updateTrainer(String logId, String logPw, String name, String region) {
        Optional<Trainer> findTrainer = trainerRepository.findByLogId(logId);

        if(!findTrainer.isEmpty()) {
            findTrainer.get().changeTrainer(logPw, name, new Address(region));
        }
    }

    public Optional<Trainer> findOne(String logId) {
        return trainerRepository.findByLogId(logId);
    }
}
