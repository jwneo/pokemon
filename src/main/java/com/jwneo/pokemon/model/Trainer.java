package com.jwneo.pokemon.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "trainer_seq_generator",
        sequenceName = "trainer_seq",
        initialValue = 1,
        allocationSize = 1
)
public class Trainer extends BaseTime {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "trainer_seq_generator"
    )
    private Long id;

    @Column(unique = true, updatable = false)
    private String loginId;

    private String password;

    private String name;

    @Embedded
    private Address address;

    private String pokeList = "";

    @Builder
    public Trainer(String loginId, String password, String name, Address address) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public void changePassword(String logPassword) {
        this.password = password;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public void updatePokeList(String pokeList) {
        this.pokeList = pokeList;
    }
}
