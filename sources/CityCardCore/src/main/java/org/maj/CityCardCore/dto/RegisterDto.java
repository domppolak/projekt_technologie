package org.maj.CityCardCore.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String login;
    private String password;
    private String email;
}