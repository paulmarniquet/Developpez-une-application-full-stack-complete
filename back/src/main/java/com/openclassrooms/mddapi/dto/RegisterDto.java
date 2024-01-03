package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.config.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 3, max = 20, message = "Le nom doit avoir entre 3 et 20 caractères")
    public String name;

    @Email(message = "Email non valide")
    @NotBlank(message = "L'email ne peut pas être vide")
    public String email;

    @ValidPassword
    private String password;
}
