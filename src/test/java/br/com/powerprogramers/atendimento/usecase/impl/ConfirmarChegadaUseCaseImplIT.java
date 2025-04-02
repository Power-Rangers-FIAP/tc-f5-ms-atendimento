package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ConfirmarChegadaUseCaseImplIT {

    @InjectMocks
    private ConfirmarChegadaUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Test
    void deveConfirmarChegadaIntegrandoDependencias() {
        String idAtendimento = "123";

        useCase.execute(idAtendimento);

        verify(atendimentoGateway).confirmarChegada(idAtendimento);
    }
}
