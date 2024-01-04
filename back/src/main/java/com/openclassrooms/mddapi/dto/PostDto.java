package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {

    @NotBlank(message = "Le contenu ne peut pas être vide")
    @NotNull(message = "Le contenu ne peut pas être vide")
    private String content;

    @NotNull(message = "L'id de l'article ne peut pas être vide")
    private Long articleId;

    @NotNull(message = "L'id de l'utilisateur ne peut pas être vide")
    private Long userId;
}
