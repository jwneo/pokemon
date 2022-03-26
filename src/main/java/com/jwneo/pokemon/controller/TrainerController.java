package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.dto.TrainerDto;
import com.jwneo.pokemon.model.Address;
import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.service.PokedexService;
import com.jwneo.pokemon.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;
    private final PokedexService pokedexService;

    @GetMapping("/trainers/join")
    public String createDto(Model model) {
        model.addAttribute("trainerDto", new TrainerDto());
        return "trainers/join";
    }

    @PostMapping("/trainers/join")
    public String join(@Valid TrainerDto trainerDto,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect::trainers/join";
        }

        Trainer trainer = Trainer.builder()
                .logId(trainerDto.getLogId())
                .logPassword(trainerDto.getLogPassword())
                .name(trainerDto.getName())
                .address(new Address(trainerDto.getRegion()))
                .build();

        trainerService.saveTrainer(trainer);

        return "home";
    }

    @GetMapping("/trainer/{id}/pokedex")
    public String getPokedex(@PathVariable("id") String trainerId, Model model) {
        Trainer trainer = trainerService.findOne(trainerId).get();
        List<Pokedex> pokedexList = pokedexService.findAll();

        String pokeList = trainer.getPokeList();
        int pokeCnt = Arrays.stream(pokeList.split("/")).filter(p -> !p.isEmpty()).toArray().length;

        model.addAttribute("pokeCnt", pokeCnt);
        model.addAttribute("pokeList", pokeList);
        model.addAttribute("pokedexList", pokedexList);
        return "trainers/pokeList";
    }

    @ResponseBody
    @PostMapping("/trainer/{id}/pokedex")
    public String updatePokedex(@PathVariable("id") String trainerId,
                                @RequestParam(value = "pokeList") String pokeList) {
        trainerService.updatePokeList(trainerId, pokeList);
        return "";
    }
}
