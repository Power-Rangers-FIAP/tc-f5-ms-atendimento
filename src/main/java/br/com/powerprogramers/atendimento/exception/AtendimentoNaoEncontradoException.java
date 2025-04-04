package br.com.powerprogramers.atendimento.exception;

public class AtendimentoNaoEncontradoException extends AtendimentoException {
  public AtendimentoNaoEncontradoException() {
    super("Atendimento não encontrado");
  }
}
