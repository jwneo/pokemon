package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.dto.TradeForm;
import com.jwneo.pokemon.dto.TrainerDto;
import com.jwneo.pokemon.dto.TrainerForm;
import com.jwneo.pokemon.model.Address;
import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trade;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.service.PokedexService;
import com.jwneo.pokemon.service.TradeService;
import com.jwneo.pokemon.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;
    private final PokedexService pokedexService;
    private final TrainerService trainerService;

    @GetMapping("/trade")
    public String list(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) TrainerDto trainerDto,
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            Model model) {

        model.addAttribute("trainerDto", trainerDto);

        Page<Trade> tradeList = tradeService.listAll(pageable);
        int start = (int) Math.floor(tradeList.getNumber() / 10) * 10 + 1;
        int last = start + 9 < tradeList.getTotalPages() ? start + 9 : tradeList.getTotalPages();

        model.addAttribute("pageNumber", pageable.getPageNumber() + 1);
        model.addAttribute("start", start);
        model.addAttribute("last", last);
        model.addAttribute("tradeList", tradeList);
        return "trades/list";
    }

    @GetMapping("/trade/{id}")
    public String view(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) TrainerDto trainerDto,
            @PathVariable("id") String tradeId,
            Model model) {

        model.addAttribute("trainerDto", trainerDto);

        Optional<Trade> trade = tradeService.findOne(Long.parseLong(tradeId));

        if (trade.isEmpty()) return "redirect:/trade";

        List<Pokedex> pokedexList = pokedexService.findAll();

        model.addAttribute("pokedexList", pokedexList);
        model.addAttribute("trade", trade.get());

        return "trades/view";
    }

    @GetMapping("/trade/write")
    public String tradeForm(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) TrainerDto trainerDto,
            Model model) {

        if (trainerDto == null) {
            return "redirect:/";
        }

        model.addAttribute("trainerDto", trainerDto);
        model.addAttribute("tradeForm", new TradeForm());

        return "trades/write";
    }

    @PostMapping("/trade/write")
    public String write(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) TrainerDto trainerDto,
            @Valid TradeForm tradeForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "trades/write";
        }

        try {
            Trainer trainer = trainerService.findOne(trainerDto.getLoginId()).get();

            Trade trade = Trade.builder()
                    .trainer(trainer)
                    .title(tradeForm.getTitle())
                    .content(tradeForm.getContent())
                    .build();

            tradeService.writeTrade(trade);

        } catch (DuplicateKeyException ex) {
            return "trades/write";
        }

        return "redirect:/trade";
    }
}
