package com.jwneo.pokemon.service;

import com.jwneo.pokemon.dto.TrainerDto;
import com.jwneo.pokemon.dto.TrainerForm;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final TrainerRepository trainerRepository;

    public TrainerDto login(String logId, String logPassword) {

        Optional<Trainer> loginTrainer = trainerRepository.findByLoginId(logId);
        TrainerDto trainerDto = new TrainerDto();

        if (!loginTrainer.isEmpty()) {
            Trainer trainer = loginTrainer.get();
            trainerDto.setLoginId(trainer.getLoginId());

            if (trainer.getPassword().equals(logPassword)) {
                trainerDto.setName(trainer.getName());
                trainerDto.setRegion(trainer.getAddress().getRegion());
                trainerDto.setPokeList(trainer.getPokeList());
            }
        }

        return trainerDto;
    }
}
