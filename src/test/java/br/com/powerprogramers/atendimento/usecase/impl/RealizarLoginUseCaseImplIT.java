package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.gateway.LoginGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RealizarLoginUseCaseImplIT {

    @InjectMocks
    private RealizarLoginUseCaseImpl useCase;

    @Mock
    private LoginGateway loginGateway;

    @Test
    void deveRealizarLoginIntegrandoDependencias() {
        Login login = new Login("user", "password");
        Token tokenEsperado = new Token("token123");

        when(loginGateway.realizarLogin(login)).thenReturn(tokenEsperado);

        Token tokenRetornado = useCase.execute(login);

        assertEquals(tokenEsperado, tokenRetornado);
        verify(loginGateway).realizarLogin(login);
    }
}