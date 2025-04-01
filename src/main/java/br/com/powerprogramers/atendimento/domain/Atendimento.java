package br.com.powerprogramers.atendimento.domain;

import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record Atendimento(
        String id,
        String idPaciente,
        String idMedico,
        String idUnidade,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        StatusAtendimento status,
        List<EnfermidadeRequest> enfermidade,
        String comentario,
        Integer numero
) {

    public static Atendimento iniciarAtendimento(RegistrarAtendimento registrarAtendimento) {
        return Atendimento.builder()
                .idPaciente(registrarAtendimento.idPaciente())
                .idUnidade(registrarAtendimento.idUnidade())
                .dataHoraInicio(ZonedDateTime.now().toLocalDateTime())
                .status(StatusAtendimento.ABERTO)
                .enfermidade(registrarAtendimento.enfermidades())
                .build();
    }

}
