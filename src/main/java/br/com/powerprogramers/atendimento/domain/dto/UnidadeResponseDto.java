package br.com.powerprogramers.atendimento.domain.dto;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record UnidadeResponseDto(String id, String nome, String endereco, Integer quantidadeMedicos, Integer quantidadePacientes, String tempoMedioAtendimento, @Valid List<EspecialidadesResponseDto> especialidades) {
}
