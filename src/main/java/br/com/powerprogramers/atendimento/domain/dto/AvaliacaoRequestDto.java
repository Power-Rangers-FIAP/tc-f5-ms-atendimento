package br.com.powerprogramers.atendimento.domain.dto;

import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Builder
@Validated
public record AvaliacaoRequestDto(String idAtendimento, Integer nota, String comentario) {
}
