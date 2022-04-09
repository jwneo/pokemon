package com.jwneo.pokemon.service;

import com.jwneo.pokemon.model.Trade;
import com.jwneo.pokemon.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;

    @Transactional
    public void writeTrade(Trade trade) {
        tradeRepository.save(trade);
    }

    public Page<Trade> listAll(Pageable pageable) {
        return tradeRepository.findAll(pageable);
    }

    public Optional<Trade> findOne(Long tradeId) {
        return tradeRepository.findById(tradeId);
    }

    @Transactional
    public void updateComment(Long tradeId, String comment) {
        Optional<Trade> findTrade = tradeRepository.findById(tradeId);

        if (!findTrade.isEmpty()) {
            findTrade.get().updateComment(comment);
        }
    }
}
