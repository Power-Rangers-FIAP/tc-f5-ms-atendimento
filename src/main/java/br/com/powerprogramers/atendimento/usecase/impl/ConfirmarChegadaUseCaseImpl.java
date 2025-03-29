package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.ConfirmarChegadaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmarChegadaUseCaseImpl implements ConfirmarChegadaUseCase {

    private final AtendimentoGateway atendimentoGateway;

    @Override
    public void execute(String idAtendimento) {
        atendimentoGateway.confirmarChegada(idAtendimento);
    }
}
