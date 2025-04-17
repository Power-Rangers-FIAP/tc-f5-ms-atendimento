package br.com.powerprogramers.atendimento.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record AvaliacaoPaginadaResponseDto(Integer pagina, Integer porPagina, Long total, List<AvaliacaoResponseDto> items) {
}
