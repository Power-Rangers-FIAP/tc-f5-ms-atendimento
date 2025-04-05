package br.com.powerprogramers.atendimento.domain;

import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Atendimento {
  private String id;
  private String idPaciente;
  private String idMedico;
  private String idUnidade;
  private Instant dataHoraInicio;
  private Instant dataHoraFim;
  private StatusAtendimento status;
  private List<EnfermidadeRequest> enfermidade;
  private String comentario;
  private Integer numero;
  private Avaliacao avaliacao;

  public static Atendimento iniciarAtendimento(RegistrarAtendimento registrarAtendimento) {
    return Atendimento.builder()
        .idPaciente(registrarAtendimento.idPaciente())
        .idUnidade(registrarAtendimento.idUnidade())
        .dataHoraInicio(ZonedDateTime.now().toInstant())
        .status(StatusAtendimento.ABERTO)
        .enfermidade(registrarAtendimento.enfermidades())
        .build();
  }

  public void definirNumero(Integer proximoNumero) {
    this.numero = proximoNumero;
  }

  public void confirmarChegada() {
    this.status = StatusAtendimento.EM_ATENDIMENTO;
  }

  public void finalizarAtendimento(String idMedico, String comentario) {
    this.status = StatusAtendimento.FINALIZADO;
    this.dataHoraFim = ZonedDateTime.now().toInstant();
    this.idMedico = idMedico;
    this.comentario = comentario;
  }

  public void registrarAvaliacao(Avaliacao avaliacao) {
    this.avaliacao = avaliacao;
  }

  public boolean estaAtrasadoAhMaisDe(Long minutosAtrasado) {
    var agora = ZonedDateTime.now().toInstant();
    return this.dataHoraInicio.plus(Duration.ofMinutes(minutosAtrasado)).isBefore(agora);
  }

}
