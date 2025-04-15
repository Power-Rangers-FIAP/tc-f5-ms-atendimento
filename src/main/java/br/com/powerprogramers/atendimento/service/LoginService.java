package br.com.powerprogramers.atendimento.service;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.Token;

public interface LoginService {

  Token login(Login login);
}
