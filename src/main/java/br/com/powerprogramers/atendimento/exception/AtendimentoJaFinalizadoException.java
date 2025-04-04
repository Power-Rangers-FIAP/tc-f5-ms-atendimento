package br.com.powerprogramers.atendimento.exception;

public class AtendimentoJaFinalizadoException extends AtendimentoException {
  public AtendimentoJaFinalizadoException() {
    super("Atendimento já finalizado");
  }
}
