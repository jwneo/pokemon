package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.service.PokedexService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PokedexService pokedexService;

    @GetMapping("/")
    public String home(Model model) {
        List<Pokedex> list = pokedexService.findAll();
        model.addAttribute("tpList", list);
        return "trainers/trainerPokedexes";
    }
}
