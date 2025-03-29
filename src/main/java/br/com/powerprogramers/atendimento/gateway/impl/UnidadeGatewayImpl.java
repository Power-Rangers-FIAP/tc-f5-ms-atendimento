package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.gateway.UnidadeGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UnidadeGatewayImpl implements UnidadeGateway {
    @Override
    public void listarUnidade(Pageable pageable) {
        //FIXME implementar o listar unidade quando o MS de unidade estiver finalizado
    }
}
