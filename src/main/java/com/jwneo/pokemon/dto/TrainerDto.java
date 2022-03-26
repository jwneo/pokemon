package com.jwneo.pokemon.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TrainerDto {
    @NotBlank(message = "아이디가 입력되지 않았습니다.")
    private String logId;
    @NotBlank(message = "비밀번호가 입력되지 않았습니다.")
    private String logPassword;
    @NotBlank(message = "이름이 입력되지 않았습니다.")
    private String name;
    private String region;
}
