package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Pagina;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.gateway.UnidadeGateway;
import br.com.powerprogramers.atendimento.usecase.IniciarAtendimentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IniciarAtendimentoUseCaseImpl implements IniciarAtendimentoUseCase {

  private final UnidadeGateway unidadeGateway;

  @Override
  public Paginacao<Unidade> execute(Pagina input) {
    PageRequest pageable = PageRequest.of(input.pagina(), input.porPagina());
    return this.unidadeGateway.listarUnidade(pageable);
  }
}
