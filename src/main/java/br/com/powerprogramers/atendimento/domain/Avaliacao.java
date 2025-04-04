package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record Avaliacao(Integer nota, String comentario) {
}
