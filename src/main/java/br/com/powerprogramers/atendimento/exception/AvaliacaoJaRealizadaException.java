package br.com.powerprogramers.atendimento.exception;

public class AvaliacaoJaRealizadaException extends AtendimentoException {
  public AvaliacaoJaRealizadaException() {
    super("Avaliação já realizada");
  }
}
