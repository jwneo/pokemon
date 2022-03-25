package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@SpringBootTest
@Transactional(readOnly = true)
class TrainerPokedexRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private PokedexRepository pokedexRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void 도감추가() throws Exception {
        //given
        Trainer trainer = Trainer.builder()
                .logId("aaddda")
                .logPassword("abcd")
                .name("지우")
                .build();
        trainerRepository.save(trainer);

        //when
        trainer.updatePokeList("이상해씨A/파이리B/꼬부기A");
        em.flush();

        //then
        List<Pokedex> trainerPokedexeList = pokedexRepository.findByPokeList(trainer.getPokeList());
        for (Pokedex pokedex : trainerPokedexeList) {
            System.out.println("pokedex = " + pokedex);
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void 도감삭제() throws Exception {
        //given
        Trainer trainer = Trainer.builder()
                .logId("abcd")
                .logPassword("abcd")
                .name("지우")
                .build();
        trainerRepository.save(trainer);

        trainer.updatePokeList("이상해씨A/파이리A/꼬부기B");

        em.flush();

        //when
        trainer.updatePokeList("");
        Trainer findTrainer = trainerRepository.findById(trainer.getId()).get();

        em.flush();
        em.clear();

        //then
        System.out.println("findTrainer = " + findTrainer.getPokeList());
    }

}