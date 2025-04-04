package br.com.powerprogramers.atendimento.exception;

public class HistoricoAtendimentoNaoEncontradoException extends AtendimentoException {
  public HistoricoAtendimentoNaoEncontradoException() {
    super("Historico de atendimento não encontrado");
  }
}
