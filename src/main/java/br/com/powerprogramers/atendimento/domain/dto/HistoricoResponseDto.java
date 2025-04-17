package br.com.powerprogramers.atendimento.domain.dto;

import java.time.OffsetDateTime;
import java.util.List;

public record HistoricoResponseDto(String id, String idPaciente, String idMedico, String idUnidade, OffsetDateTime dataHoraInicio, OffsetDateTime dataHoraFim, String status, List<EnfermidadeDto> enfermidade, String comentario) {
}
