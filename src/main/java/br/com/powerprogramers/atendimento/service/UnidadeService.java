package br.com.powerprogramers.atendimento.service;

import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import org.springframework.data.domain.Pageable;

public interface UnidadeService {

  Paginacao<Unidade> listarUnidade(Pageable pageable);
}
