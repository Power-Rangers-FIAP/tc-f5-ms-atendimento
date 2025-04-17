package br.com.powerprogramers.atendimento.domain.dto;

public record AvaliacaoResponseDto(String idUnidade, String idMedico, Integer nota, String comentario) {
}
