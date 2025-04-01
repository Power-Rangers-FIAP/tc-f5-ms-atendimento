package br.com.powerprogramers.atendimento.gateway;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import org.springframework.data.domain.Page;

public interface HistoricoGateway {

    Page<Atendimento> consultarHistorico(ConsultarHistorico input);

}
