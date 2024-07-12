package br.com.animeapi.domain.dto.request;

import br.com.animeapi.domain.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public class AnimeRequestUpdateDto {
    @NotBlank(message = "Name can't be empty")
    private String name;
    @NotBlank(message = "Description can't be empty")
    private String description;
    @NotNull(message = "Release Date can't be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
    @NotNull(message = "category can't be null")
    private Category category;
    @NotBlank(message = "whereToWatch can't be empty")
    private String whereToWatch;
    @NotBlank(message = "image can't be empty")
    private String image;


    public AnimeRequestUpdateDto() {
    }

    public AnimeRequestUpdateDto(String name, String description, LocalDate releaseDate, Category category, String whereToWatch, String image) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.category = category;
        this.whereToWatch = whereToWatch;
        this.image = image;
    }

    public @NotBlank(message = "Name can't be empty") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name can't be empty") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Description can't be empty") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description can't be empty") String description) {
        this.description = description;
    }

    public @NotNull(message = "Release Date can't be null") LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NotNull(message = "Release Date can't be null") LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public @NotNull(message = "category can't be null") Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull(message = "category can't be null") Category category) {
        this.category = category;
    }

    public @NotBlank(message = "whereToWatch can't be empty") String getWhereToWatch() {
        return whereToWatch;
    }

    public void setWhereToWatch(@NotBlank(message = "whereToWatch can't be empty") String whereToWatch) {
        this.whereToWatch = whereToWatch;
    }

    public @NotBlank(message = "image can't be empty") String getImage() {
        return image;
    }

    public void setImage(@NotBlank(message = "image can't be empty") String image) {
        this.image = image;
    }
}
