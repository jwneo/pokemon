package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.model.Trade;
import com.jwneo.pokemon.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @ResponseBody
    @GetMapping("/trade")
    public Page<Trade> list(@PageableDefault(size = 10) Pageable pageable) {
        return tradeService.listAll(pageable);
    }
}
