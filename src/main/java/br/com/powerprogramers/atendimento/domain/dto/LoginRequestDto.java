package br.com.powerprogramers.atendimento.domain.dto;

import lombok.Builder;

@Builder
public record LoginRequestDto(String usuario, String senha) {
}
