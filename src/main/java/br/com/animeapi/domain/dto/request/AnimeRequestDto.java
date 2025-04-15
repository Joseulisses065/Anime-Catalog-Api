package br.com.animeapi.domain.dto.request;

import br.com.animeapi.domain.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class AnimeRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotBlank(message = "Name can't be empty")
    private String name;
    @NotBlank(message = "Description can't be empty")
    private String description;
    @NotNull(message = "Release Date can't be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    @NotNull(message = "category can't be null")
    private Category category;
    @NotBlank(message = "whereToWatch can't be empty")
    @JsonProperty("where_to_watch")
    private String whereToWatch;
    @NotBlank(message = "image can't be empty")
    private String image;


    public AnimeRequestDto() {
    }

    public AnimeRequestDto(String name, String description, LocalDate releaseDate, Category category, String whereToWatch, String image) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.category = category;
        this.whereToWatch = whereToWatch;
        this.image = image;
    }

    public @NotBlank(message = "image can't be empty") String getImage() {
        return image;
    }

    public void setImage(@NotBlank(message = "image can't be empty") String image) {
        this.image = image;
    }

    public @NotBlank @NotNull String getName() {
        return name;
    }

    public void setName(@NotBlank @NotNull String name) {
        this.name = name;
    }

    public @NotBlank @NotNull String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank @NotNull String description) {
        this.description = description;
    }

    public @NotNull LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NotNull LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public @NotNull Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull Category category) {
        this.category = category;
    }

    public @NotBlank @NotNull String getWhereToWatch() {
        return whereToWatch;
    }

    public void setWhereToWatch(@NotBlank @NotNull String whereToWatch) {
        this.whereToWatch = whereToWatch;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnimeRequestDto that = (AnimeRequestDto) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(releaseDate, that.releaseDate) && category == that.category && Objects.equals(whereToWatch, that.whereToWatch) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, releaseDate, category, whereToWatch, image);
    }

    @Override
    public String toString() {
        return "AnimeRequestDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", category=" + category +
                ", whereToWatch='" + whereToWatch + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
