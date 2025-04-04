package br.com.powerprogramers.atendimento.exception;

public class ConsultaHistoricoInvalidaException extends AtendimentoException {
  public ConsultaHistoricoInvalidaException() {
    super("Informar somente um dos campos: idPaciente ou idAtendimento");
  }
}
