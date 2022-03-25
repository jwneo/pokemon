package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.service.PokedexService;
import com.jwneo.pokemon.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;
    private final PokedexService pokedexService;

    @GetMapping("/trainer/{id}/pokedex")
    public String createPokedex(@PathVariable("id") String trainerId, Model model) {
        Trainer trainer = trainerService.findOne(trainerId).get();
        List<Pokedex> pokedexList = pokedexService.findAll();

        String pokeList = trainer.getPokeList();
        int pokeCnt = pokeList.split("/").length;

        model.addAttribute("pokeCnt", pokeCnt);
        model.addAttribute("pokeList", pokeList);
        model.addAttribute("pokedexList", pokedexList);
        return "trainers/trainerPokedexList";
    }

    @ResponseBody
    @PostMapping("/trainer/{id}/pokedex")
    public String savePokedex(@PathVariable("id") String trainerId,
                              @RequestParam(value = "pokeList") String pokeList) {
        trainerService.updatePokeList(trainerId, pokeList);
        return "";
    }
}
