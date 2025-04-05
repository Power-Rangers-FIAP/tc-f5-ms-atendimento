package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.gateway.LoginGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RealizarLoginUseCaseImplTest {

    private RealizarLoginUseCaseImpl useCase;

    @Mock
    private LoginGateway loginGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new RealizarLoginUseCaseImpl(loginGateway);
    }

    @Test
    void deveRealizarLoginComSucesso() {
        Login login = new Login("user", "password");
        Token tokenEsperado = new Token("token123");

        when(loginGateway.realizarLogin(login)).thenReturn(tokenEsperado);

        Token tokenRetornado = useCase.execute(login);

        assertEquals(tokenEsperado, tokenRetornado);
        verify(loginGateway).realizarLogin(login);
    }
}