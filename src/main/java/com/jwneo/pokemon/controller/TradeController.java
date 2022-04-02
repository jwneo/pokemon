package com.jwneo.pokemon.controller;

import com.jwneo.pokemon.model.Trade;
import com.jwneo.pokemon.service.TradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TradeController {

    private final TradeService tradeService;

    @GetMapping("/trade")
    public String list(@PageableDefault(size = 10, sort = {"id"} ,direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<Trade> tradeList = tradeService.listAll(pageable);
        int start = (int) Math.floor(tradeList.getNumber() / 10) * 10 + 1;
        int last = start + 9 < tradeList.getTotalPages() ? start + 9 : tradeList.getTotalPages();

        model.addAttribute("pageNumber", pageable.getPageNumber()+1);
        model.addAttribute("start", start);
        model.addAttribute("last", last);
        model.addAttribute("tradeList", tradeList);
        return "trades/list";
    }
}
