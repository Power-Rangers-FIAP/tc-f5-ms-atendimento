package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.gateway.UnidadeGateway;
import br.com.powerprogramers.atendimento.service.UnidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnidadeGatewayImpl implements UnidadeGateway {

  private final UnidadeService unidadeService;

  @Override
  public Paginacao<Unidade> listarUnidade(Pageable pageable) {
    return this.unidadeService.listarUnidade(pageable);
  }
}
