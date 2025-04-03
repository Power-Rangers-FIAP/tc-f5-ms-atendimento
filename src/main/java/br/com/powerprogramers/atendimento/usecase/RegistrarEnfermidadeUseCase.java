package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;

public interface RegistrarEnfermidadeUseCase extends UseCase<RegistrarAtendimento, Atendimento> {

  @Override
  Atendimento execute(RegistrarAtendimento input);
}
