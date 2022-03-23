package com.jwneo.pokemon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "trainer_seq_generator",
        sequenceName = "trainer_seq",
        initialValue = 1,
        allocationSize = 1
)
@EntityListeners(AuditingEntityListener.class)
public class Trainer extends BaseTime implements Persistable<String> {

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
            , generator = "trainer_seq_generator"
    )
    private Long no;

    @Id
    @Column(updatable = false)
    private String id;

    private String password;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private final List<TrainerPokedex> trainerPokedexes = new ArrayList<>();

    @Builder
    public Trainer(String id, String password, String name, Address address) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
