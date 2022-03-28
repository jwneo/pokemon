package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.dto.LoginForm;
import com.jwneo.pokemon.dto.TrainerForm;
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
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm,
                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        TrainerForm login = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        return "redirect:/";
    }
}
