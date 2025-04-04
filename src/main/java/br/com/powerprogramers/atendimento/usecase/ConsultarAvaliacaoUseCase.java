package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarAvaliacao;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;

public interface ConsultarAvaliacaoUseCase
    extends UseCase<ConsultarAvaliacao, Paginacao<Atendimento>> {

  @Override
  Paginacao<Atendimento> execute(ConsultarAvaliacao input);
}
