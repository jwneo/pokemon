package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.model.TrainerPokedex;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional(readOnly = true)
class TrainerPokedexRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private PokedexRepository pokedexRepository;
    @Autowired
    private TrainerPokedexRepository trainerPokedexRepository;

    @Test
    @Transactional
    @Commit
    public void 도감추가() throws Exception {
        //given
        Trainer trainer = Trainer.builder()
                .logId("aaa")
                .logPassword("abcd")
                .name("지우")
                .build();
        trainerRepository.save(trainer);

        //when
        List<Pokedex> pokedexes = pokedexRepository.findAll();
        trainerPokedexRepository.save(new TrainerPokedex(trainer, pokedexes.get(5)));
        trainerPokedexRepository.save(new TrainerPokedex(trainer, pokedexes.get(6)));
        trainerPokedexRepository.save(new TrainerPokedex(trainer, pokedexes.get(7)));

        em.flush();

        //then
        List<TrainerPokedex> trainerPokedexes = trainer.getTrainerPokedexes();
        for (TrainerPokedex trainerPokedex : trainerPokedexes) {
            System.out.println("trainerPokedex = " + trainerPokedex.getPokedex());
        }
    }

    @Test
    @Transactional
    @Commit
    public void 도감삭제() throws Exception {
        //given
        Trainer trainer = Trainer.builder()
                .logId("aaa")
                .logPassword("abcd")
                .name("지우")
                .build();
        trainerRepository.save(trainer);

        List<Pokedex> pokedexes = pokedexRepository.findAll();
        trainerPokedexRepository.save(new TrainerPokedex(trainer, pokedexes.get(5)));
        trainerPokedexRepository.save(new TrainerPokedex(trainer, pokedexes.get(6)));
        trainerPokedexRepository.save(new TrainerPokedex(trainer, pokedexes.get(7)));

        //when
        trainerPokedexRepository.deleteByTrainer(trainer);

        //then
        List<TrainerPokedex> trainerPokedexes = trainerPokedexRepository.findByTrainer(trainer);

        System.out.println("trainer = " + trainer.getTrainerPokedexes().size());
        System.out.println("pokedexes = " + trainerPokedexes.size());
    }

}