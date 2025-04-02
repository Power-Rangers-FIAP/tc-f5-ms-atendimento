package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginGatewayImplIT {

    @InjectMocks
    private LoginGatewayImpl loginGateway;

    @Mock
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        reset(loginService);
    }

    @Test
    void deveConfirmarFluxoCompletoDeLogin() {
        Login login = new Login("user@example.com", "senha123");
        Token tokenEsperado = new Token("jwt-token");

        when(loginService.login(login.email(), login.senha())).thenReturn(tokenEsperado);

        Token resultado = loginGateway.realizarLogin(login);

        assertNotNull(resultado);
        verify(loginService, times(1)).login(login.email(), login.senha());
    }
}