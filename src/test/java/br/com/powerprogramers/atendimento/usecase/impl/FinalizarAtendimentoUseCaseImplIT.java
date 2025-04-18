package br.com.powerprogramers.atendimento.usecase.impl;

import static org.mockito.Mockito.*;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FinalizarAtendimentoUseCaseImplIT {

    @InjectMocks
    private FinalizarAtendimentoUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Mock
    private ControleAtendimentoGateway controleAtendimentoGateway;

    @Test
    void deveFinalizarAtendimentoComSucesso() {
        FinalizarAtendimento input = new FinalizarAtendimento("1", "2", "muito bom", "DOCTOR");
        Atendimento atendimentoMock = new Atendimento();

        when(atendimentoGateway.getById(input.idAtendimento())).thenReturn(atendimentoMock);

        useCase.execute(input);

        verify(atendimentoGateway).save(atendimentoMock);
        verify(controleAtendimentoGateway).atualizarQuantidadePacientes(any(), eq(-1));
    }
}
