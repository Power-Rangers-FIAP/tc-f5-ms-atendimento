package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record RegistrarAtendimento(String idUnidade, String idPaciente, List<EnfermidadeRequest> enfermidades) {
}
