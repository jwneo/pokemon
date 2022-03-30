package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.dto.TrainerDto;
import com.jwneo.pokemon.dto.TrainerForm;
import com.jwneo.pokemon.model.Address;
import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping("/trainer/join")
    public String trainerForm(Model model) {
        model.addAttribute("trainerForm", new TrainerForm());
        return "trainers/join";
    }

    @PostMapping("/trainer/join")
    public String joinTrainer(@Valid TrainerForm trainerForm,
                              BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "trainers/join";
        }

        try {
            Optional<Trainer> findTrainer = trainerService.findOne(trainerForm.getLoginId());

            if (!findTrainer.isEmpty())
                throw new DuplicateKeyException("중복아이디");

            Trainer trainer = Trainer.builder()
                    .loginId(trainerForm.getLoginId())
                    .password(trainerForm.getPassword())
                    .name(trainerForm.getName())
                    .address(new Address(trainerForm.getRegion()))
                    .build();
            trainerService.createTrainer(trainer);
        } catch (DuplicateKeyException ex) {
            bindingResult.rejectValue("loginId", "duplicate", "사용중인 아이디입니다.");
            return "trainers/join";
        }

        return "redirect:/";
    }

    @GetMapping("/trainer/{id}")
    public String updateTrainerForm(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) TrainerDto trainerDto,
            @PathVariable("id") String loginId,
            Model model) {

        if (trainerDto == null || !trainerDto.getLoginId().equals(loginId)) {
            return "redirect:/";
        }

        model.addAttribute("trainerDto", trainerDto);

        TrainerForm trainerForm = new TrainerForm();
        trainerForm.setLoginId(trainerDto.getLoginId());
        trainerForm.setName(trainerDto.getName());
        trainerForm.setRegion(trainerDto.getRegion());
        model.addAttribute("trainerForm", trainerForm);

        return "trainers/modify";
    }

    @PostMapping("/trainer/{id}")
    public String updateTrainer(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) TrainerDto trainerDto,
            @Valid TrainerForm trainerForm,
            BindingResult bindingResult,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("trainerDto", trainerDto);

        if (bindingResult.hasErrors()) {
            return "trainers/modify";
        }

        trainerService.updateTrainer(trainerDto.getLoginId(), trainerForm);

        trainerDto.setName(trainerForm.getName());
        trainerDto.setRegion(trainerForm.getRegion());

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, trainerDto);

        return "redirect:/";
    }

    @GetMapping("/trainer/{id}/pokedex")
    public String createTrainerPokedex(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) TrainerDto trainerDto,
            @SessionAttribute(name = SessionConst.POKEDEX, required = false) List<Pokedex> pokedexList,
            @PathVariable("id") String loginId,
            Model model) {

        if (trainerDto == null || !trainerDto.getLoginId().equals(loginId)) {
            return "redirect:/";
        }

        model.addAttribute("trainerDto", trainerDto);

        String pokeList = trainerDto.getPokeList();
        int pokeCnt = Arrays.stream(pokeList.split("/")).filter(p -> !p.isEmpty()).toArray().length;

        model.addAttribute("pokeCnt", pokeCnt);
        model.addAttribute("pokedexList", pokedexList);
        return "trainers/pokeList";
    }

    @ResponseBody
    @PostMapping("/trainer/{id}/pokedex")
    public String updateTrainerPokedex(
            HttpServletRequest request,
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) TrainerDto trainerDto,
            @RequestParam(value = "pokeList") String pokeList) {

        trainerService.updatePokeList(trainerDto.getLoginId(), pokeList);
        trainerDto.setPokeList(pokeList);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, trainerDto);

        return "";
    }
}
