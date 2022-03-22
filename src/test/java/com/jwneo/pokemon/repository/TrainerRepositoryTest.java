package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Address;
import com.jwneo.pokemon.model.Trainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional(readOnly = true)
class TrainerRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TrainerRepository trainerRepository;

    @Test
    @Transactional
    @Commit
    public void save() throws Exception {
        //given
        Trainer trainer = Trainer.builder()
                .logId("aaaa")
                .logPassword("abcd")
                .name("몽카생")
                .address(new Address("신현동"))
                .build();
        trainerRepository.save(trainer);

        //when
        Trainer findTrainer = trainerRepository.findByLogId(trainer.getLogId()).get();

        //then
        assertThat(findTrainer.getName()).isEqualTo(trainer.getName());
    }
}