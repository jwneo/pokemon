package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.dto.LoginForm;
import com.jwneo.pokemon.dto.TrainerDto;
import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.service.LoginService;
import com.jwneo.pokemon.service.PokedexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final PokedexService pokedexService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        TrainerDto login = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (login.getLoginId() == null) {
            bindingResult.rejectValue("loginId", "NotFoundLoginId", "존재하지 않는 아이디입니다.");
            return "login";
        }

        if (login.getName() == null) {
            bindingResult.rejectValue("password", "WrongPassword", "비밀번호가 맞지 않습니다.");
            return "login";
        }

        List<Pokedex> pokedexList = pokedexService.findAll();

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, login);
        session.setAttribute(SessionConst.POKEDEX, pokedexList);
        return "redirect:/";
    }
}
