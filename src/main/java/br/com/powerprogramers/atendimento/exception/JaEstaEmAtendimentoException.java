package br.com.powerprogramers.atendimento.exception;

public class JaEstaEmAtendimentoException extends AtendimentoException {
  public JaEstaEmAtendimentoException() {
    super("Já está em atendimento");
  }
}
