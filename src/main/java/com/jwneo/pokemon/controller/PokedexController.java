package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.service.PokedexService;
import com.jwneo.pokemon.service.TrainerPokedexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PokedexController {

    private final PokedexService pokedexService;
    private final TrainerPokedexService trainerPokedexService;

    @GetMapping("/pokedex")
    public List<Pokedex> findAllPokedex() {
        return pokedexService.findAll();
    }

    @GetMapping("/pokedex/{code}")
    public List<Pokedex> findPokedex(@PathVariable("code") String code) {
        return pokedexService.findByCode(code);
    }

}
