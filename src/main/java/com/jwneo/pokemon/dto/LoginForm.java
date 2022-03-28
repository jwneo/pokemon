package com.jwneo.pokemon.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginForm {
    @NotBlank(message = "아이디가 입력되지 않았습니다.")
    private String loginId;
    @NotBlank(message = "비밀번호가 입력되지 않았습니다.")
    private String password;
}
