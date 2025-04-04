package br.com.powerprogramers.atendimento.exception;

public class JaPossuiRegistroAtendimentoEmAbertoException extends AtendimentoException {
  public JaPossuiRegistroAtendimentoEmAbertoException() {
    super("Paciente já possui atendimento em aberto");
  }
}
