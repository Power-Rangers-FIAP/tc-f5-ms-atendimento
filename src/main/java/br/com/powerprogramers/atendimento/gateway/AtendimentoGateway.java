package br.com.powerprogramers.atendimento.gateway;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarAvaliacao;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import org.springframework.data.domain.Page;

public interface AtendimentoGateway {

  Atendimento getById(String idAtendimento);

  Atendimento save(Atendimento atendimento);

  Atendimento existeAtendimentoAberto(String idPaciente);

  Page<Atendimento> consultarHistorico(ConsultarHistorico input);

  Page<Atendimento> consultarAvaliacao(ConsultarAvaliacao input);

}
