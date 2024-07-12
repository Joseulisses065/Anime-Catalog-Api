package br.com.animeapi.domain.entitites;

import br.com.animeapi.domain.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity()
@Table(name = "animes")
public class Anime {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "text", nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    @Column(nullable = false, length = 1024)
    private String image;
    @Column(nullable = false)
    private String whereToWatch;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Anime() {
    }

    public Anime(UUID id, String name, String description, LocalDate releaseDate, Category category, String image, String whereToWatch, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.category = category;
        this.image = image;
        this.whereToWatch = whereToWatch;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
