package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Avaliacao;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.entity.AvaliacaoEntity;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.AvaliarAtendimentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliarAtendimentoUseCaseImpl implements AvaliarAtendimentoUseCase {

    private final AtendimentoGateway atendimentoGateway;

    @Override
    public void execute(Avaliacao input) {

        AtendimentoEntity atendimento = atendimentoGateway.getById(input.idAtendimento());

        if (!StatusAtendimento.FINALIZADO.equals(atendimento.getStatus()) ) {
            throw new RuntimeException(); //FIXME usar exceção customizada
        }

        var avaliacaoEntity = AvaliacaoEntity.builder()
                .atendimento(atendimento)
                .nota(input.nota())
                .comentario(input.comentario())
                .build();

        atendimentoGateway.save(avaliacaoEntity);
    }
}
