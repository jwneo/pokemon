package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.dto.TrainerDto;
import com.jwneo.pokemon.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginDto(Model model) {
        model.addAttribute("trainerDto", new TrainerDto());
        return "trainers/login";
    }

    @PostMapping("/login")
    public String login(@Valid TrainerDto trainerDto,
                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/trainers/login";
        }

        TrainerDto login = loginService.login(trainerDto.getLogId(), trainerDto.getLogPassword());

        return "redirect:/";
    }
}
