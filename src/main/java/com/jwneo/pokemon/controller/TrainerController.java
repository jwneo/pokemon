package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.service.PokedexService;
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

    @GetMapping("/trainer/{id}/pokedex")
    public String trainerPokedex(@PathVariable("id") String trainerId, Model model) {
        Trainer trainer = trainerService.findOne(trainerId).get();
        List<Pokedex> pokedexList = pokedexService.findAll();

        model.addAttribute("pokeList", trainer.getPokeList());
        model.addAttribute("pokedexList", pokedexList);
        return "trainers/trainerPokedexList";
    }

    @PostConstruct
    public void init() {
        Trainer trainer = Trainer.builder()
                .logId("aaa")
                .logPassword("abcd")
                .name("지우")
                .build();
        trainer.updatePokeList("이상해씨A/파이리B/꼬부기B");
        try{
            trainerService.saveTrainer(trainer);
        } catch (Exception ex) {

        }
    }
}
