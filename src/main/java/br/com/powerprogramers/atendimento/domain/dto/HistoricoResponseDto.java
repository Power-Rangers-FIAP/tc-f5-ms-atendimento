package br.com.powerprogramers.atendimento.domain.dto;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.List;

@Validated
public record HistoricoResponseDto(String id, String idPaciente, String idMedico, String idUnidade, OffsetDateTime dataHoraInicio, OffsetDateTime dataHoraFim, String status, @Valid List<EnfermidadeDto> enfermidade, String comentario) {
}
