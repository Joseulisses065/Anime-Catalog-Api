package br.com.animeapi.repositories;

import br.com.animeapi.domain.entitites.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, UUID> {
}
