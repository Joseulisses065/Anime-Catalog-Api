package br.com.animeapi.web.controllers;

import br.com.animeapi.domain.dto.request.AnimeRequestDto;
import br.com.animeapi.domain.dto.request.AnimeRequestUpdateDto;
import br.com.animeapi.domain.dto.response.AnimeResponseDto;
import br.com.animeapi.services.AnimeService;
import br.com.animeapi.web.exception.ErrorMenssage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
@Tag(name = "AnimeApi", description = "Contains all operations related to a Anime resource")
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    @Operation(summary = "Add new animes", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inserted successfully", content = @Content(mediaType = "application/json",schema = @Schema(implementation =AnimeResponseDto.class))),
            @ApiResponse(responseCode = "422" , description = "Unprocessable entity", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMenssage.class)))
    })
    @PostMapping
    public ResponseEntity<AnimeResponseDto> createAnime( @Valid  @RequestBody AnimeRequestDto anime) {
        AnimeResponseDto response = animeService.createAnime(anime);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Find all animes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found successfully",content = @Content(mediaType = "application/json",schema = @Schema(implementation = AnimeResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMenssage.class)))
    })
    @GetMapping
    public ResponseEntity<Page<AnimeResponseDto>> findAll(Pageable page) {
        var response = animeService.getAnimes(page);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find anime by id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found successfully",content = @Content(mediaType = "application/json",schema = @Schema(implementation = AnimeResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMenssage.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMenssage.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> findById(@PathVariable UUID id) {
        var response = animeService.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update anime by id", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated successfully",content = @Content(mediaType = "application/json",schema = @Schema(implementation = AnimeResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMenssage.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnimeResponseDto> updateAnime(@PathVariable UUID id,@Valid @RequestBody AnimeRequestUpdateDto anime) {
        var response = animeService.updateAnime(id, anime);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete anime by id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully",content = @Content(mediaType = "application/json",schema = @Schema(implementation = AnimeResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMenssage.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable UUID id) {
        animeService.deleteAnime(id);
        return ResponseEntity.noContent().build();
    }
}
