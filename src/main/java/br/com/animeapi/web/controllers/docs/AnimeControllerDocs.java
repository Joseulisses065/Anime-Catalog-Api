package br.com.animeapi.web.controllers.docs;

import br.com.animeapi.domain.dto.request.AnimeRequestDto;
import br.com.animeapi.domain.dto.request.AnimeRequestUpdateDto;
import br.com.animeapi.domain.dto.response.AnimeResponseDto;
import br.com.animeapi.web.exception.ErrorMenssage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Tag(name = "Anime", description = "Contains all operations related to a Anime resource")
public interface AnimeControllerDocs {
    @Operation(summary = "Add New Anime",tags = {"Anime"},description = "Add New Anime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inserted Successfully", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AnimeResponseDto.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class)))
    })
    @PostMapping
    ResponseEntity<AnimeResponseDto> createAnime(@Valid @RequestBody AnimeRequestDto anime);

    @Operation(summary = "Find All Anime",tags = {"Anime"},description = "Find All Anime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found successfully",content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = AnimeResponseDto.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class)))
    })
     ResponseEntity<PagedModel<EntityModel<AnimeResponseDto>>> findAll(@RequestParam(name = "page",defaultValue = "0") Integer page,
                                                                             @RequestParam(name = "size",defaultValue = "12") Integer size,
                                                                             @RequestParam(name = "direction",defaultValue = "asc") String direction);

        @Operation(summary = "Find Anime By Id",tags = {"Anime"},description = "Find Anime By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found successfully", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AnimeResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class)))
    })
    ResponseEntity<AnimeResponseDto> findById(@PathVariable UUID id);

    @Operation(summary = "Update Anime By Id",tags = {"Anime"},description = "Update Anime By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated successfully", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AnimeResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class)))
    })
    ResponseEntity<AnimeResponseDto> updateAnime(@PathVariable UUID id, @Valid @RequestBody AnimeRequestUpdateDto anime);

    @Operation(summary = "Delete Anime By Id", tags = {"Anime"},description = "Delete Anime By Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content( mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMenssage.class)))
    })
    ResponseEntity<Void> deleteAnime(@PathVariable UUID id);
    }


