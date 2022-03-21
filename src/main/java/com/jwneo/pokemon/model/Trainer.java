package com.jwneo.pokemon.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(TrainerId.class)
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
    private Long id;

    @Column(name = "log_id")
    private String logId;

    @Column(name = "log_password")
    private String logPassword;

    private String name;

    @Embedded
    private Address address;

    public void changeName(String name) {
        this.name = name;
    }
}
