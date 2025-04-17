package br.com.powerprogramers.atendimento.domain.dto;

public record UnidadeResponseDto(String id, String nome, String endereco, Integer quantidadeMedicos, Integer quantidadePacientes, String tempoMedioAtendimento) {
}
