package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record EnfermidadeRequest(String id, String comentario) {
}
