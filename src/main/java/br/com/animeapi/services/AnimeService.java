package br.com.animeapi.services;

import br.com.animeapi.domain.dto.request.AnimeRequestDto;
import br.com.animeapi.domain.dto.request.AnimeRequestUpdateDto;
import br.com.animeapi.domain.dto.response.AnimeResponseDto;
import br.com.animeapi.domain.entitites.Anime;
import br.com.animeapi.exceptions.EntityNotFoundException;
import br.com.animeapi.repositories.AnimeRepository;
import br.com.animeapi.web.controllers.AnimeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.animeapi.domain.dto.mapper.EntityMapper.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;
    private Pageable pageable = new Pageable() {
        @Override
        public int getPageNumber() {
            return 0;
        }

        @Override
        public int getPageSize() {
            return 0;
        }

        @Override
        public long getOffset() {
            return 0;
        }

        @Override
        public Sort getSort() {
            return null;
        }

        @Override
        public Pageable next() {
            return null;
        }

        @Override
        public Pageable previousOrFirst() {
            return null;
        }

        @Override
        public Pageable first() {
            return null;
        }

        @Override
        public Pageable withPage(int pageNumber) {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    };

    public AnimeResponseDto createAnime(AnimeRequestDto anime) {
        Anime entity = mapObject(anime, Anime.class);
        entity.setCreatedAt(LocalDateTime.now());
        animeRepository.save(entity);
        var response = mapObject(entity, AnimeResponseDto.class);
        response.add(linkTo(methodOn(AnimeController.class).createAnime(anime)).withSelfRel());
        return response;
    }

    public Page<AnimeResponseDto> getAnimes(Pageable page) {
        var entity = animeRepository.findAll(page);
        var response = mapPageObject(entity, AnimeResponseDto.class);
        response.stream().forEach(res -> res.add(linkTo(methodOn(AnimeController.class).findAll(pageable)).withSelfRel()));
        return response;
    }

    public AnimeResponseDto findById(UUID id) {
        var entity = animeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Anime with id{%s} not found", id)));
        var response = mapObject(entity, AnimeResponseDto.class);
        response.add(linkTo(methodOn(AnimeController.class).findById(id)).withSelfRel());
        return response;
    }


    public AnimeResponseDto updateAnime(UUID id, AnimeRequestUpdateDto anime) {
        var entity = animeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Anime with id{%s} not found", id)));
        entity.setUpdatedAt(LocalDateTime.now());
        mapper.map(anime, entity);
        var response = mapObject(animeRepository.save(entity), AnimeResponseDto.class);
        response.add(linkTo(methodOn(AnimeController.class).updateAnime(id,anime)).withSelfRel());
        return response;
    }

    public AnimeResponseDto deleteAnime(UUID id) {
        var entity = animeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Anime with id{%s} not found", id)));
        animeRepository.delete(entity);
        return mapObject(entity, AnimeResponseDto.class);
    }
}
