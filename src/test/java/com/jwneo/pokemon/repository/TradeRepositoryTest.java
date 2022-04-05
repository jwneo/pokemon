package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Trade;
import com.jwneo.pokemon.model.Trainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class TradeRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void 교환등록() throws Exception {
        //given
        Trainer trainer = trainerRepository.findByLoginId("jwneo").get();
        Trade trade = Trade.builder()
                .trainer(trainer)
                .title("tes1111111111111")
                .content("test입니다")
                .build();
        tradeRepository.save(trade);

        //when
        em.flush();
        em.clear();

        List<Trade> findTrade = tradeRepository.findByTrainer(trainer);

        //then
        assertThat(findTrade).isEqualTo(trade);

    }
}