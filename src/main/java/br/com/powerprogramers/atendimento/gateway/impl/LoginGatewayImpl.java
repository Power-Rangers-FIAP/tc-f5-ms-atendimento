package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.gateway.LoginGateway;
import br.com.powerprogramers.atendimento.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginGatewayImpl implements LoginGateway {

  private final LoginService loginService;

  @Override
  public Token realizarLogin(Login input) {
    return this.loginService.login(input.email(), input.senha());
  }
}
