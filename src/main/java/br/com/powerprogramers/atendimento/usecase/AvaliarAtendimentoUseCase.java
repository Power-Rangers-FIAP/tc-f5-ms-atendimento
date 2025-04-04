package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.RegistrarAvaliacao;

public interface AvaliarAtendimentoUseCase extends UnitUseCase<RegistrarAvaliacao> {

  @Override
  void execute(RegistrarAvaliacao input);
}
