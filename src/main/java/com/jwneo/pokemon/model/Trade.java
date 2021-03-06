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
        name = "trade_seq_generator",
        sequenceName = "trade_seq",
        initialValue = 1,
        allocationSize = 1
)
public class Trade extends BaseTime {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trade_seq_generator"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name="login_id")
    private Trainer trainer;

    private String title;

    private String content;

    private String comment = "";

    @Builder
    public Trade(Trainer trainer, String title, String content) {
        this.trainer = trainer;
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }
}
