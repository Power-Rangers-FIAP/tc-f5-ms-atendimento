package br.com.powerprogramers.atendimento.exception;

public class LoginException extends AtendimentoException {
  public LoginException() {
    super("Falha ao realizar login");
  }
}
