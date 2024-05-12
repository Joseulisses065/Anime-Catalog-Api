package br.com.animeapi.controllers;

import br.com.animeapi.domain.dto.request.AnimeRequestDto;
import br.com.animeapi.domain.dto.response.AnimeResponseDto;
import br.com.animeapi.services.AnimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/animes")
public class AnimeController {


    @Autowired
    private AnimeService animeService;

    @GetMapping()
    public String helloWord() {
        return "hello word";
    }

    @PostMapping
    public ResponseEntity<Void> createAnime(@RequestBody @Valid AnimeRequestDto anime, UriComponentsBuilder builder) {
        AnimeResponseDto response = animeService.createAnime(anime);
        URI uri = builder.path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).build();


    }
}
