package br.com.movieflix.controller.response;

import lombok.Builder;

@Builder
public record CategoryReponse(Long id, String name) {
}
