package com.jwneo.pokemon.dto;

import lombok.Data;

@Data
public class TrainerDto {
    private String loginId;
    private String name;
    private String region;
    private String pokeList;
}
