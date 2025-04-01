package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.gateway.LoginGateway;
import br.com.powerprogramers.atendimento.usecase.RealizarLoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealizarLoginUseCaseImpl implements RealizarLoginUseCase {

    private final LoginGateway loginGateway;

    @Override
    public Token execute(Login input) {
        return loginGateway.realizarLogin(input);
    }
}
