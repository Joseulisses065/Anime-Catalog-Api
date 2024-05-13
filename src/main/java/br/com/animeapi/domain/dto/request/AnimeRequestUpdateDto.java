package br.com.animeapi.domain.dto.request;

import br.com.animeapi.domain.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public class AnimeRequestUpdateDto {
    @NotBlank
    @NotNull
    private String name;

    public AnimeRequestUpdateDto() {
    }

    public AnimeRequestUpdateDto(String name) {
        this.name = name;
    }

    public @NotBlank @NotNull String getName() {
        return name;
    }

    public void setName(@NotBlank @NotNull String name) {
        this.name = name;
    }
}
