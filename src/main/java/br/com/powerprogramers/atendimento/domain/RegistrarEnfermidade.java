package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record RegistrarEnfermidade(String id, String idUnidade, List<EnfermidadeRequest> enfermidades, Integer numero) {
}
