package br.com.powerprogramers.atendimento.domain.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Builder
@Validated
public record UnidadePaginadaResponseDto(Integer pagina, Integer porPagina, Long total, @Valid List<UnidadeResponseDto> items) {
}
