package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.model.TrainerPokedex;
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
    @Autowired
    private TrainerPokedexRepository trainerPokedexRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void 트레이너도감추가() throws Exception {
        //given
        Trainer trainer = Trainer.builder()
                .id("aaddda")
                .password("abcd")
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
    @Rollback(value = false)
    public void 도감삭제() throws Exception {
        //given
        Trainer trainer = Trainer.builder()
                .id("abcd")
                .password("abcd")
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