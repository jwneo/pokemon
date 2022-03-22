package com.jwneo.pokemon.repository;

import com.jwneo.pokemon.model.Pokedex;
import com.jwneo.pokemon.model.Trainer;
import com.jwneo.pokemon.model.TrainerPokedex;
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
    public void save() throws Exception {
        //given
        Trainer trainer = Trainer.builder()
                .logId("aaa")
                .logPassword("abcd")
                .name("지우")
                .build();
        trainerRepository.save(trainer);

        List<Pokedex> pokedexList = new ArrayList<>();
        List<TrainerPokedex> trainerPokedexList = new ArrayList<>();

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Pokedex pokedex = Pokedex.builder()
                    .code(Integer.toString(i))
                    .name("A" + i)
                    .build();
            pokedexList.add(pokedex);

            TrainerPokedex trainerPokedex = TrainerPokedex.builder()
                    .trainer(trainer)
                    .pokedex(pokedex)
                    .build();
            trainerPokedexList.add(trainerPokedex);
        });

        pokedexRepository.saveAll(pokedexList);
        trainerPokedexRepository.saveAll(trainerPokedexList);

        //when
        List<TrainerPokedex> pokedexes = trainer.getTrainerPokedexes();
        System.out.println("pokedexes.size() = " + pokedexes.size());

        //then

    }

}