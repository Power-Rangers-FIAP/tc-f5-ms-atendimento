package br.com.powerprogramers.atendimento.gateway;

import org.springframework.data.domain.Pageable;

public interface UnidadeGateway {

    void listarUnidade(Pageable pageable); //FIXME ajustar depois de ter o retorno de unidade

}
