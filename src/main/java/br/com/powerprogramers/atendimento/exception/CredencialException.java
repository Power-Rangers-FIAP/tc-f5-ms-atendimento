package br.com.powerprogramers.atendimento.exception;

public class CredencialException extends AtendimentoException {
  public CredencialException() {
    super("Credenciais inválidas");
  }
}
