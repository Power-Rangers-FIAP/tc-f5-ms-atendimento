package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.exception.ConsultaHistoricoInvalidaException;
import br.com.powerprogramers.atendimento.exception.HistoricoAtendimentoNaoEncontradoException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.ConsultarHistoricoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultarHistoricoUseCaseImpl implements ConsultarHistoricoUseCase {

  private final AtendimentoGateway atendimentoGateway;

  @Override
  public Paginacao<Atendimento> execute(ConsultarHistorico input) {

    if (!input.chavePesquisaValida()) {
      throw new ConsultaHistoricoInvalidaException();
    }

    var atendimentosFinalizados = this.atendimentoGateway.consultarHistorico(input);

    if (atendimentosFinalizados == null) {
      throw new HistoricoAtendimentoNaoEncontradoException();
    }

    return new Paginacao<>(
        atendimentosFinalizados.getNumber(),
        atendimentosFinalizados.getSize(),
        atendimentosFinalizados.getNumberOfElements(),
        atendimentosFinalizados.getContent());
  }
}
