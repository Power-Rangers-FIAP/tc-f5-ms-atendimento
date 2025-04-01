package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.RegistrarEnfermidadeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrarEnfermidadeUseCaseImpl implements RegistrarEnfermidadeUseCase {

    private final AtendimentoGateway atendimentoGateway;

    @Override
    public Atendimento execute(RegistrarAtendimento input) {
        return atendimentoGateway.registrarEnfermidade(input);
    }
}
