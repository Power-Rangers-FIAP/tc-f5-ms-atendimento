package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.Atendimento;

public interface ConsultarAtendimentoUseCase
    extends UseCase<String, Atendimento> {

  @Override
  Atendimento execute(String input);
}
