package br.com.powerprogramers.atendimento.service.impl;

import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.domain.Usuario;
import br.com.powerprogramers.atendimento.exception.CredencialException;
import br.com.powerprogramers.atendimento.exception.LoginException;
import br.com.powerprogramers.atendimento.service.LoginService;
import java.util.Arrays;
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
  public Token login(String email, String senha) {
    String url = String.format("%s", loginApiUrl);
    var usuarios = restTemplate.getForObject(url, Usuario[].class);

    Usuario usuario = null;
    if (usuarios != null) {
      usuario =
          Arrays.stream(usuarios)
              .filter(u -> email.equals(u.email()))
              .findFirst()
              .orElseThrow(LoginException::new);
      if (senha.equals(usuario.senha())) {
        return new Token(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWRQYWNpZW50ZSI6ImNmMjkwYjljLWQ5NTgtNDk1Ni05ODhkLWM4YjNhZjk1NzdjZCIsImlhdCI6MTUxNjIzOTAyMn0.h22ECTmRscoooy-A91SOK1G9G_t6aGWSxTsDMYPms7c");
      }
    }

    throw new CredencialException();
  }
}
