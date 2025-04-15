package br.com.powerprogramers.atendimento.service.impl;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.exception.CredencialException;
import br.com.powerprogramers.atendimento.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  @Value("${login.api.url}")
  private String loginApiUrl;

  private final RestTemplate restTemplate;

  @Override
  public Token login(Login login) {

    var token = restTemplate.postForObject(loginApiUrl, login, Token.class);

    if (token != null) {
      return token;
    }

    throw new CredencialException();
  }
}
