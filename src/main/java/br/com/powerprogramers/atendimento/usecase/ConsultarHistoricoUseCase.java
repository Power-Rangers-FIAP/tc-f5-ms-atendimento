package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;

public interface ConsultarHistoricoUseCase
    extends UseCase<ConsultarHistorico, Paginacao<Atendimento>> {

  @Override
  Paginacao<Atendimento> execute(ConsultarHistorico input);
}
