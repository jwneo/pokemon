package com.jwneo.pokemon.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
//@IdClass(TrainerId.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "trainer_seq_generator",
        sequenceName = "trainer_seq",
        initialValue = 1,
        allocationSize = 1
)
public class Trainer implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "trainer_seq_generator"
    )
    @Column(name = "trainer_id")
    private Long id;

    @Column(name = "log_id", unique = true, nullable = false, updatable = false)
    private String logId;

    @Column(name = "log_password", nullable = false)
    private String logPassword;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "trainer")
    private final List<TrainerPokedex> trainerPokedexes = new ArrayList<>();

    @Builder
    public Trainer(String logId, String logPassword, String name, Address address) {
        this.logId = logId;
        this.logPassword = logPassword;
        this.name = name;
        this.address = address;
    }

    public void changeTrainer(String logPassword, String name, Address address) {
        this.logPassword = logPassword;
        this.name = name;
        this.address = address;
    }
}
