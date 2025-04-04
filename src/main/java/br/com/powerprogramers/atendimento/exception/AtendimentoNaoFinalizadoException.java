package br.com.powerprogramers.atendimento.exception;

public class AtendimentoNaoFinalizadoException extends AtendimentoException {
  public AtendimentoNaoFinalizadoException() {
    super("Atendimento não finalizado");
  }
}
