package br.com.powerprogramers.atendimento.exception;

public class AvaliacaoNaoEncontradoException extends AtendimentoException {
  public AvaliacaoNaoEncontradoException() {
    super("Nenhuma avaliação encontrada");
  }
}
