package br.com.powerprogramers.atendimento.usecase.impl;
import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinalizarAtendimentoUseCaseImplIT {

    @InjectMocks
    private FinalizarAtendimentoUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Test
    void deveFinalizarAtendimentoComSucesso() {
        FinalizarAtendimento input = new FinalizarAtendimento("1", "2", "3");

        useCase.execute(input);

        verify(atendimentoGateway, times(1)).finalizarAtendimento(input);
    }
}
