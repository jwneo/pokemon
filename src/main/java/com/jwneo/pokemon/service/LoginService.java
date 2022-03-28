package com.jwneo.pokemon.service;

import com.jwneo.pokemon.dto.TrainerForm;
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

    public TrainerForm login(String logId, String logPassword) {

        try {
            Trainer trainer = trainerRepository.findByLoginId(logId).get();

            if (trainer.getPassword().equals(logPassword)) {
                TrainerForm trainerForm = new TrainerForm();
                trainerForm.setLoginId(trainer.getLoginId());
                trainerForm.setName(trainer.getName());
                trainerForm.setRegion(trainer.getAddress().getRegion());

                return trainerForm;
            }

        } catch (Exception ex) {

        }
        return null;
    }
}
