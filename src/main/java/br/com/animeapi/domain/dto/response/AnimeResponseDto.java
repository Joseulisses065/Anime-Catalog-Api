package br.com.animeapi.domain.dto.response;

import br.com.animeapi.domain.enums.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "release_date",
        "category",
        "whereTo_watch",
        "image",
        "created_at",
        "updated_at",
        "_links"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnimeResponseDto extends RepresentationModel<AnimeResponseDto> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID id;
    private String name;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    private Category category;
    @JsonProperty("whereTo_watch")
    private String whereToWatch;
    private String image;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty("updated_at")
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

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AnimeResponseDto that = (AnimeResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(releaseDate, that.releaseDate) && category == that.category && Objects.equals(whereToWatch, that.whereToWatch) && Objects.equals(image, that.image) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, description, releaseDate, category, whereToWatch, image, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "AnimeResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", category=" + category +
                ", whereToWatch='" + whereToWatch + '\'' +
                ", image='" + image + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
