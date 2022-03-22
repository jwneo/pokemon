package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;



}
