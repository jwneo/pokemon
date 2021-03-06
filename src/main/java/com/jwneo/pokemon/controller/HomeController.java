package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.dto.TrainerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) TrainerDto trainerDto,
            Model model) {

        model.addAttribute("trainerDto", trainerDto);
        return "home";
    }
}
