package br.com.animeapi.web.controllers;

import br.com.animeapi.domain.dto.request.AnimeRequestDto;
import br.com.animeapi.domain.dto.request.AnimeRequestUpdateDto;
import br.com.animeapi.domain.dto.response.AnimeResponseDto;
import br.com.animeapi.services.AnimeService;
import br.com.animeapi.web.controllers.docs.AnimeControllerDocs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/anime")
public class AnimeController implements AnimeControllerDocs {
    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @PostMapping(produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<AnimeResponseDto> createAnime(@RequestBody @Valid AnimeRequestDto anime) {
        AnimeResponseDto response = animeService.createAnime(anime);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Override
    @GetMapping( produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PagedModel<EntityModel<AnimeResponseDto>>> findAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                             @RequestParam(name = "size", defaultValue = "12") Integer size,
                                                                             @RequestParam(name = "direction", defaultValue = "asc") String direction) {
        Sort.Direction directionEnum = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(size, page, Sort.by(directionEnum, "name"));
        var response = animeService.getAllAnime(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<AnimeResponseDto> findById(@PathVariable @Valid UUID id) {
        var response = animeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<AnimeResponseDto> updateAnime(@PathVariable @Valid UUID id, @RequestBody @Valid AnimeRequestUpdateDto anime) {
        var response = animeService.updateAnime(id, anime);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> deleteAnime(@PathVariable @Valid UUID id) {
        animeService.deleteAnime(id);
        return ResponseEntity.noContent().build();
    }
}
