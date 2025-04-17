package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record RegistrarAvaliacao(String idAtendimento, String idPaciente, Integer nota, String comentario) {
}
