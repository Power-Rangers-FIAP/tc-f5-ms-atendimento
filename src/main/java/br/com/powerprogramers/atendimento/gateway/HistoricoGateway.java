package br.com.powerprogramers.atendimento.gateway;

import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import org.springframework.data.domain.Page;

public interface HistoricoGateway {

    Page<AtendimentoEntity> consultarHistorico(ConsultarHistorico input);

}
