package br.com.powerprogramers.atendimento.gateway;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import org.springframework.data.domain.Page;

public interface AtendimentoGateway {

  Atendimento getById(String idAtendimento);

  Atendimento save(Atendimento atendimento);

  boolean existeAtendimentoAberto(String idAtendimento);

  Page<Atendimento> consultarHistorico(ConsultarHistorico input);
}
