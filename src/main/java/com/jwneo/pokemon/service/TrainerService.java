package com.jwneo.pokemon.service;

import com.jwneo.pokemon.dto.TrainerForm;
import com.jwneo.pokemon.model.Address;
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
    public void createTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    @Transactional
    public void updateTrainer(String trainerId, TrainerForm trainerForm) {
        Optional<Trainer> findTrainer = trainerRepository.findByLoginId(trainerId);

        if (!findTrainer.isEmpty()) {
            Trainer trainer = findTrainer.get();
            trainer.changePassword(trainerForm.getPassword());
            trainer.changeName(trainerForm.getName());
            trainer.changeAddress(new Address(trainerForm.getRegion()));
        }
    }

    @Transactional
    public void updatePokeList(String trainerId, String pokeList) {
        Optional<Trainer> findTrainer = trainerRepository.findByLoginId(trainerId);

        if (!findTrainer.isEmpty()) {
            findTrainer.get().updatePokeList(pokeList);
        }
    }

    public Optional<Trainer> findOne(String loginId) {
        return trainerRepository.findByLoginId(loginId);
    }
}
