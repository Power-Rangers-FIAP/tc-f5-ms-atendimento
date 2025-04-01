package br.com.powerprogramers.atendimento.gateway;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.Token;

public interface LoginGateway {

    Token realizarLogin(Login input);

}
