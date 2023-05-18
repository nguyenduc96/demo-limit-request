package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class Customer {

    @NotEmpty
    private String username;

    private String password;

    private String email;

    private String phone;
}
