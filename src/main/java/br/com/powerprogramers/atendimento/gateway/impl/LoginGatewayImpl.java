package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.gateway.LoginGateway;
import org.springframework.stereotype.Service;

@Service
public class LoginGatewayImpl implements LoginGateway {
    @Override
    public void realizarLogin(Login input) {
        //FIXME implementar o login quando o MS de login estiver finalizado
    }
}
