package br.com.animeapi.services;

import br.com.animeapi.domain.dto.request.AnimeRequestDto;
import br.com.animeapi.domain.dto.request.AnimeRequestUpdateDto;
import br.com.animeapi.domain.dto.response.AnimeResponseDto;
import br.com.animeapi.domain.entitites.Anime;
import br.com.animeapi.exceptions.EntityNotFoundException;
import br.com.animeapi.repositories.AnimeRepository;
import br.com.animeapi.web.controllers.AnimeController;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.animeapi.domain.dto.mapper.EntityMapper.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;
    private final PagedResourcesAssembler<AnimeResponseDto> pagedResourcesAssembler;

    public AnimeService(AnimeRepository animeRepository, PagedResourcesAssembler<AnimeResponseDto> pagedResourcesAssembler) {
        this.animeRepository = animeRepository;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    public AnimeResponseDto createAnime(AnimeRequestDto anime) {
        Anime entity = mapObject(anime, Anime.class);
        entity.setCreatedAt(LocalDateTime.now());
        animeRepository.save(entity);
        var response = mapObject(entity, AnimeResponseDto.class);
        addHateoas(response);
        return response;
    }

    private static void addHateoas(AnimeResponseDto response) {
        AnimeRequestDto animeRequestDto = mapObject(response, AnimeRequestDto.class);
        AnimeRequestUpdateDto animeRequestUpdateDto = mapObject(response, AnimeRequestUpdateDto.class);
        response.add(linkTo(methodOn(AnimeController.class).findById(response.getId())).withSelfRel().withType("GET"));
        response.add(linkTo(methodOn(AnimeController.class).findAll(0,12, "asc")).withRel("find_all").withType("GET"));
        response.add(linkTo(methodOn(AnimeController.class).createAnime(animeRequestDto)).withRel("create").withType("POST"));
        response.add(linkTo(methodOn(AnimeController.class).findById(response.getId())).withRel("findById").withType("GET"));
        response.add(linkTo(methodOn(AnimeController.class).updateAnime(response.getId(),animeRequestUpdateDto)).withRel("update").withType("PUT"));
        response.add(linkTo(methodOn(AnimeController.class).deleteAnime(response.getId())).withRel("delete").withType("DELETE"));
    }

    public PagedModel<EntityModel<AnimeResponseDto>> getAllAnime(Pageable page) {
        var entity = animeRepository.findAll(page);
        var response = mapPageObject(entity, AnimeResponseDto.class);
        response.map(r->{
            addHateoas(r);
            return r;
        });
        Link link = linkTo(methodOn(AnimeController.class).findAll(page.getPageNumber(),page.getPageSize(),page.getSort().toString())).withSelfRel();
        return pagedResourcesAssembler.toModel(response, link);
    }

    public AnimeResponseDto findById(UUID id) {
        var entity = animeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Anime with id{%s} not found", id)));
        var response = mapObject(entity, AnimeResponseDto.class);
        addHateoas(response);
        return response;
    }


    public AnimeResponseDto updateAnime(UUID id, AnimeRequestUpdateDto anime) {
        var entity = animeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Anime with id{%s} not found", id)));
        entity.setUpdatedAt(LocalDateTime.now());
        mapper.map(anime, entity);
        var response = mapObject(animeRepository.save(entity), AnimeResponseDto.class);
        addHateoas(response);
        return response;
    }

    public void deleteAnime(UUID id) {
        var entity = animeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Anime with id{%s} not found", id)));
        animeRepository.delete(entity);
    }
}
