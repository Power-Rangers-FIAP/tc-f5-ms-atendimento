package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginGatewayImplTest {

    @InjectMocks
    private LoginGatewayImpl loginGateway;

    @Mock
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRealizarLoginComSucesso() {
        Login login = new Login("user@example.com", "senha123");
        Token tokenEsperado = new Token("jwt-token");

        when(loginService.login(login.email(), login.senha())).thenReturn(tokenEsperado);

        Token resultado = loginGateway.realizarLogin(login);

        assertEquals(tokenEsperado, resultado);
        verify(loginService, times(1)).login(login.email(), login.senha());
    }
}