package br.com.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(@Schema(type = "long", description = "Código do filme")
                            Long id,

                            @Schema(type = "string", description = "Nome do filme")
                            String title,

                            @Schema(type = "string", description = "Descrição do filme")
                            String description,

                            @Schema(type = "date", description = "Data de lançamento do filme ex: '19/10/1998'")
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                            LocalDate releaseDate,

                            @Schema(type = "double", description = "Score do filme ex: 7.8")
                            double rating,

                            @Schema(type = "array", description = "Lista de códigos de categorias")
                            List<CategoryReponse> categories,

                            @Schema(type = "array", description = "Lista de códigos de serviços de streaming")
                            List<StreamingResponse> streamings) {
}
