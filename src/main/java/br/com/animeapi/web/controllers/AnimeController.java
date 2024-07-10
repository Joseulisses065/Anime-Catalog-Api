package br.com.animeapi.controllers;

import br.com.animeapi.domain.dto.request.AnimeRequestDto;
import br.com.animeapi.domain.dto.request.AnimeRequestUpdateDto;
import br.com.animeapi.domain.dto.response.AnimeResponseDto;
import br.com.animeapi.services.AnimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "AnimeApi")
public class AnimeController {


    @Autowired
    private AnimeService animeService;

    @Operation(summary = "Add new animes", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inserted successfully")

    })
    @PostMapping
    public ResponseEntity<Void> createAnime(@RequestBody @Valid AnimeRequestDto anime, UriComponentsBuilder builder) {
        AnimeResponseDto response = animeService.createAnime(anime);
        URI uri = builder.path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).build();


    }

    @Operation(summary = "Find all animes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<Page<AnimeResponseDto>> findAll(Pageable page) {
        var response = animeService.getAnimes(page);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Find anime by id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> findById(@PathVariable UUID id) {
        var response = animeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update anime by id", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated successfully"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @PutMapping("/{id}")
    public  ResponseEntity<Void> updateAnime(@PathVariable UUID id, @RequestBody AnimeRequestUpdateDto anime){
        animeService.updateAnime(id,anime);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete anime by id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Not Found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable UUID id){
        animeService.deleteAnime(id);
        return ResponseEntity.noContent().build();
    }
}
