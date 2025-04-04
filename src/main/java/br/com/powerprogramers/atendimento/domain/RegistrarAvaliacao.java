package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record RegistrarAvaliacao(String idAtendimento, Integer nota, String comentario) {
}
