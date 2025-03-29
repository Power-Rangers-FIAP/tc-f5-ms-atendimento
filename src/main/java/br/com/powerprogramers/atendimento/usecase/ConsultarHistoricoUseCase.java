package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.Historico;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;

public interface ConsultarHistoricoUseCase extends UseCase<ConsultarHistorico, Paginacao<Historico>> {

    @Override
    Paginacao<Historico> execute(ConsultarHistorico input);

}
