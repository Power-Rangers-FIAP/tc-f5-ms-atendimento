package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.RegistrarEnfermidade;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.RegistrarEnfermidadeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrarEnfermidadeUseCaseImpl implements RegistrarEnfermidadeUseCase {

    private final AtendimentoGateway atendimentoGateway;

    @Override
    public RegistrarEnfermidade execute(RegistrarEnfermidade input) {
        return atendimentoGateway.registrarEnfermidade(input);
    }
}
