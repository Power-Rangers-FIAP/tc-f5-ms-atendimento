package br.com.powerprogramers.atendimento.domain.dto;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Builder
@Validated
public record LoginRequestDto(String usuario, String senha) {
}
