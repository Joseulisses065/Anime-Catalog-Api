package br.com.animeapi.controllers;

import br.com.animeapi.domain.dto.request.AnimeRequestDto;
import br.com.animeapi.domain.dto.request.AnimeRequestUpdateDto;
import br.com.animeapi.domain.dto.response.AnimeResponseDto;
import br.com.animeapi.services.AnimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {


    @Autowired
    private AnimeService animeService;

    @PostMapping
    public ResponseEntity<Void> createAnime(@RequestBody @Valid AnimeRequestDto anime, UriComponentsBuilder builder) {
        AnimeResponseDto response = animeService.createAnime(anime);
        URI uri = builder.path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).build();


    }

    @GetMapping
    public ResponseEntity<Page<AnimeResponseDto>> findAll(Pageable page) {
        var response = animeService.getAnimes(page);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> findById(@PathVariable UUID id) {
        var response = animeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Void> updateAnime(@PathVariable UUID id, @RequestBody AnimeRequestUpdateDto anime){
        animeService.updateAnime(id,anime);
        return ResponseEntity.noContent().build();
    }
}
