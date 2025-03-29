package br.com.powerprogramers.atendimento.domain;

import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Historico(
        String id,
        String pacienteId,
        String medicoId,
        String unidadeId,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        StatusAtendimento status,
        String comentario
) {
}
