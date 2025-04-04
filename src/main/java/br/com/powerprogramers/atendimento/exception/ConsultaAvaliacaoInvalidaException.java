package br.com.powerprogramers.atendimento.exception;

public class ConsultaAvaliacaoInvalidaException extends AtendimentoException {
  public ConsultaAvaliacaoInvalidaException() {
    super("Informar somente um dos campos: idUnidade ou idAtendimento");
  }
}
