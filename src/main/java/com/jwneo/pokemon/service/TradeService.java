package com.jwneo.pokemon.service;

import com.jwneo.pokemon.model.Trade;
import com.jwneo.pokemon.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;

    public Page<Trade> listAll(Pageable pageable) {
        return tradeRepository.findAll(pageable);
    }
}
