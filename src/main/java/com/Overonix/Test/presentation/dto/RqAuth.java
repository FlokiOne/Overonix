package com.Overonix.Test.presentation.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RqAuth {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

