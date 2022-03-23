package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.TrainerPokedex;
import com.jwneo.pokemon.service.TrainerPokedexService;
import com.jwneo.pokemon.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;
    private final TrainerPokedexService trainerPokedexService;

    @GetMapping("/trainer/{id}/pokedex")
    public String listPokedex(@PathVariable("id") String trainerId, Model model) {
        List<Pokedex> list = trainerPokedexService.findAll(trainerId);
        model.addAttribute("tpList", list);

        return "trainers/trainerPokedexes";
    }

}
