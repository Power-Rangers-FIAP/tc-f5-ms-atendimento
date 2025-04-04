package br.com.powerprogramers.atendimento.gateway;

import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import org.springframework.data.domain.Pageable;

public interface UnidadeGateway {

  Paginacao<Unidade> listarUnidade(Pageable pageable);
}
