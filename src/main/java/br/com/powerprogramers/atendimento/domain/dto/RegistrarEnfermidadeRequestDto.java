package br.com.powerprogramers.atendimento.domain.dto;

import java.util.List;

public record RegistrarEnfermidadeRequestDto(String idUnidade, List<EnfermidadeDto> enfermidades) {
}
