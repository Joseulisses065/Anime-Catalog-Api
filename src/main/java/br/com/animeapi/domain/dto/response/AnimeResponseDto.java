package br.com.animeapi.domain.dto.response;

import br.com.animeapi.domain.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimeResponseDto extends RepresentationModel<AnimeResponseDto> {
    private UUID id;
    private String name;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
    private Category category;
    private String whereToWatch;
    private String image;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime updatedAt;

    public AnimeResponseDto() {
    }

    public AnimeResponseDto(UUID id, String name, String description, LocalDate releaseDate, Category category, String whereToWatch, String image, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.category = category;
        this.whereToWatch = whereToWatch;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getWhereToWatch() {
        return whereToWatch;
    }

    public void setWhereToWatch(String whereToWatch) {
        this.whereToWatch = whereToWatch;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


}
