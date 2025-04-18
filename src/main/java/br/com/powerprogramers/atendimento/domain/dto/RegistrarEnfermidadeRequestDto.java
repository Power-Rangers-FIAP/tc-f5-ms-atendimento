package br.com.powerprogramers.atendimento.domain.dto;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record RegistrarEnfermidadeRequestDto(String idUnidade, @Valid List<EnfermidadeDto> enfermidades) {
}
