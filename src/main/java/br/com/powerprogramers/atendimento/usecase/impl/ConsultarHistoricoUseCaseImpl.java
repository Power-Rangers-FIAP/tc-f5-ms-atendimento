package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.Historico;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.gateway.HistoricoGateway;
import br.com.powerprogramers.atendimento.usecase.ConsultarHistoricoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultarHistoricoUseCaseImpl implements ConsultarHistoricoUseCase {

    private final HistoricoGateway historicoGateway;

    @Override
    public Paginacao<Historico> execute(ConsultarHistorico input) {

        if (!input.chavePesquisaValida()) {
            throw new RuntimeException(); //FIXME adicionar exceção personalizada
        }

        var atendimentoHistorico = historicoGateway.consultarHistorico(input);

        if (atendimentoHistorico == null) {
            throw new RuntimeException(); //FIXME adicionar exceção personalizada
        }

        return new Paginacao<>(
                atendimentoHistorico.getNumber(),
                atendimentoHistorico.getSize(),
                atendimentoHistorico.getNumberOfElements(),
                atendimentoHistorico.map(AtendimentoEntity::toHistorico).toList()
        );
    }
}
