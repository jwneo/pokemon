package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.model.TrainerPokedex;
import com.jwneo.pokemon.service.PokedexService;
import com.jwneo.pokemon.service.TrainerPokedexService;
import com.jwneo.pokemon.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;
    private final PokedexService pokedexService;
    private final TrainerPokedexService trainerPokedexService;

    @GetMapping("/trainer/{id}/pokedex")
    public String listPokedex(@PathVariable("id") String trainerId, Model model) {
        List<TrainerPokedex> list = trainerPokedexService.findAll(trainerId);
        model.addAttribute("tpList", list);

        return "trainers/trainerPokedexes";
    }

    @PostConstruct
    public void init() {
        Trainer trainer = Trainer.builder()
                .id("aaa")
                .password("abcd")
                .name("지우")
                .build();
        try{
            trainerService.saveTrainer(trainer);
        } catch (Exception ex) {

        }
    }
}
