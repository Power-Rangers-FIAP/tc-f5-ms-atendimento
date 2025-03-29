package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.Token;

public interface RealizarLoginUseCase extends UseCase<Login, Token> {

    @Override
    Token execute(Login input);

}
