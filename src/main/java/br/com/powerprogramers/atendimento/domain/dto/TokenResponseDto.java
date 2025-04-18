package br.com.powerprogramers.atendimento.domain.dto;

import org.springframework.validation.annotation.Validated;

@Validated
public record TokenResponseDto(String token) {
}
