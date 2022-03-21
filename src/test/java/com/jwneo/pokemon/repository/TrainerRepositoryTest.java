package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Address;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.model.TrainerId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class TrainerRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    TrainerRepository trainerRepository;

    @Test
    @Transactional
    @Commit
    public void 회원가입() throws Exception {
        //given
        Trainer trainer = Trainer.builder()
                .logId("aaaa")
                .logPassword("abcd")
                .name("몽카생")
                .address(new Address("신현동"))
                .build();
        trainerRepository.save(trainer);

        em.flush();

        //when
        Trainer findTrainer = trainerRepository.findByLogId(trainer.getLogId()).get();
        findTrainer.changeName("바보");

        em.flush();
        em.clear();

        findTrainer = trainerRepository.findByLogId(trainer.getLogId()).get();

        //then
        assertThat(findTrainer.getName()).isEqualTo(trainer.getName());
    }

}