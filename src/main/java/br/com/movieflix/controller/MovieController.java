package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.mapper.MovieMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Movie", description = "Recurso responsável pelo gerenciamento dos filmes.")
public interface MovieController {


    @Operation(summary = "Salvar filme", description = "Método responsável por realizar o salvamento de um novo filme.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201",description = "Filme salvo com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request);

    @Operation(summary = "Buscar filmes", description = "Método responsável por realizar a busca e retornar todos os filmes cadastrados.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200",description = "Retorna todos os filmes cadastrados",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    ResponseEntity<List<MovieResponse>> getAll();

    @Operation(summary = "Buscar filme por id", description = "Método responsável por realizar a busca e retornar um filme com um id específico.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200",description = "Filme salvo com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404",description = "Filme não encontrado.  ",
            content = @Content())
    ResponseEntity<MovieResponse> getById(@PathVariable Long id);

    @Operation(summary = "Alterar dados de um filme", description = "Método responsável por alterar dados de um item.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200",description = "Filme alterado com sucesso.",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404",description = "Filme não encontrado.",
            content = @Content())
    ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request);

    @Operation(summary = "Buscar filme por categoria", description = "Método responsável por buscar filmes por categoria.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200",description = "Filmes encontrados com sucesso",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category);

    @Operation(summary = "Deletar filme por id", description = "Método responsável por deletar um filme pelo id.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204",description = "Filme deletado com sucesso.", content = @Content())
    @ApiResponse(responseCode = "404",description = "Filme não encontrado.",  content = @Content())
    ResponseEntity<Void> deleteById(@PathVariable Long id);

}
