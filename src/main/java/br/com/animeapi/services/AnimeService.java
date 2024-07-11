package br.com.animeapi.services;

import br.com.animeapi.domain.dto.request.AnimeRequestDto;
import br.com.animeapi.domain.dto.request.AnimeRequestUpdateDto;
import br.com.animeapi.domain.dto.response.AnimeResponseDto;
import br.com.animeapi.domain.entitites.Anime;
import br.com.animeapi.exceptions.EntityNotFoundException;
import br.com.animeapi.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import  static br.com.animeapi.domain.dto.mapper.EntityMapper.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;



    public AnimeResponseDto createAnime(AnimeRequestDto anime) {
        Anime entity = mapObject(anime, Anime.class);
        entity.setCreatedAt(LocalDateTime.now());
        animeRepository.save(entity);
        return mapObject(entity, AnimeResponseDto.class);
    }

    public Page<AnimeResponseDto> getAnimes(Pageable page) {
       var entity = animeRepository.findAll(page);
       return mapPageObject(entity, AnimeResponseDto.class);
    }

    public AnimeResponseDto findById(UUID id) {
        var entity = animeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Anime with id{%s} not found",id)));
        return mapObject(entity, AnimeResponseDto.class);
    }


    public AnimeResponseDto updateAnime(UUID id, AnimeRequestUpdateDto anime) {
        var entity = animeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Anime with id{%s} not found",id)));
        entity.setUpdatedAt(LocalDateTime.now());
        mapper.map(anime,entity);
       return mapObject(animeRepository.save(entity),AnimeResponseDto.class);
    }

    public AnimeResponseDto deleteAnime(UUID id) {
        var entity = animeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Anime with id{%s} not found",id)));
        animeRepository.delete(entity);
        return mapObject(entity,AnimeResponseDto.class);
    }
}
