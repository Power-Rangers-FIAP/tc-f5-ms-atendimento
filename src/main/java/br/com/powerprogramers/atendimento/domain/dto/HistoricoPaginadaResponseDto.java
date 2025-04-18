package br.com.powerprogramers.atendimento.domain.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Builder
@Validated
public record HistoricoPaginadaResponseDto(Integer pagina, Integer porPagina, Long total, @Valid List<HistoricoResponseDto> items) {
}
