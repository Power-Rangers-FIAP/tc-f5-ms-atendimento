package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.FinalizarAtendimentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinalizarAtendimentoUseCaseImpl implements FinalizarAtendimentoUseCase {

    private final AtendimentoGateway atendimentoGateway;

    @Override
    public void execute(FinalizarAtendimento input) {
        atendimentoGateway.finalizarAtendimento(input);
    }
}
