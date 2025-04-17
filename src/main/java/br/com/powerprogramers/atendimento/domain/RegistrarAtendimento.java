package br.com.powerprogramers.atendimento.domain;

import java.util.List;
import lombok.Builder;

@Builder
public record RegistrarAtendimento(String idUnidade, String idPaciente, List<EnfermidadeRequest> enfermidades) {
}
