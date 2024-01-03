package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {

    @NotBlank(message = "L'email ou le nom d'utilisateur ne peut pas être vide")
    private String emailOrUsername;

    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    private String password;
}
