package com.jwneo.pokemon.service;

import com.jwneo.pokemon.dto.TrainerDto;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final TrainerRepository trainerRepository;

    public TrainerDto login(String logId, String logPassword) {

        try {
            Trainer trainer = trainerRepository.findByLogId(logId).get();

            if (trainer.getLogPassword().equals(logPassword)) {
                TrainerDto trainerDto = new TrainerDto();
                trainerDto.setLogId(trainer.getLogId());
                trainerDto.setLogPassword(trainer.getLogPassword());
                trainerDto.setRegion(trainer.getAddress().getRegion());

                return trainerDto;
            }

        } catch (Exception ex) {

        }
        return null;
    }
}
