package com.jwneo.pokemon.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private String logId;

    private String logPassword;

    private String name;

    @Embedded
    private Address address;

    private String pokeList = "";

    @Builder
    public Trainer(String logId, String logPassword, String name, Address address) {
        this.logId = logId;
        this.logPassword = logPassword;
        this.name = name;
        this.address = address;
    }

    public void changePassword(String logPassword) {
        this.logPassword = logPassword;
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
