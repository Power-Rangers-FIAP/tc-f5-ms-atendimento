package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarAvaliacao;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.exception.AvaliacaoNaoEncontradoException;
import br.com.powerprogramers.atendimento.exception.ConsultaAvaliacaoInvalidaException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.ConsultarAvaliacaoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultarAvaliacaoUseCaseImpl implements ConsultarAvaliacaoUseCase {

  private final AtendimentoGateway atendimentoGateway;

  @Override
  public Paginacao<Atendimento> execute(ConsultarAvaliacao input) {

    if (input == null || !input.chavePesquisaValida()) {
      throw new ConsultaAvaliacaoInvalidaException();
    }

    var atendimentosComAvaliacao = this.atendimentoGateway.consultarAvaliacao(input);

    if (atendimentosComAvaliacao == null) {
      throw new AvaliacaoNaoEncontradoException();
    }

    return new Paginacao<>(
            atendimentosComAvaliacao.getNumber(),
            atendimentosComAvaliacao.getSize(),
            atendimentosComAvaliacao.getNumberOfElements(),
            atendimentosComAvaliacao.getContent());
  }
}
