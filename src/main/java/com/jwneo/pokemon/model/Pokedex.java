package com.jwneo.pokemon.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SequenceGenerator(
        name = "pokedex_seq_generator",
        sequenceName = "pokedex_seq",
        initialValue = 1,
        allocationSize = 159
)
public class Pokedex {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "pokedex_seq_generator"
    )
    @Column(name = "pokedex_id")
    private Long id;

    private String code;

    private String name;
}
