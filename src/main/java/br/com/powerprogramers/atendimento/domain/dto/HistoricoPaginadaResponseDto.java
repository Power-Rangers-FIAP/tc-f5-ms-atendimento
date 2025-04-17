package br.com.powerprogramers.atendimento.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record HistoricoPaginadaResponseDto(Integer pagina, Integer porPagina, Long total, List<HistoricoResponseDto> items) {
}
