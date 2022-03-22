package com.jwneo.pokemon.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SequenceGenerator(
        name = "trainer_pokedex_seq_generator",
        sequenceName = "trainer_pokedex_seq",
        initialValue = 1,
        allocationSize = 159
)
public class TrainerPokedex {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "trainer_pokedex_seq_generator"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
//    @JoinColumns({
//            @JoinColumn(name = "trainer_id"),
//            @JoinColumn(name = "log_id")
//    })
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokedex_id")
    private Pokedex pokedex;

    @Builder
    public TrainerPokedex(Trainer trainer, Pokedex pokedex) {
        this.trainer = trainer;
        this.pokedex = pokedex;
        trainer.getTrainerPokedexes().add(this);
    }
}
