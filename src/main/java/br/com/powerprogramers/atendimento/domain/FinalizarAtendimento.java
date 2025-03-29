package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record FinalizarAtendimento(String idAtendimento, String idMedico, String comentario) {

}
