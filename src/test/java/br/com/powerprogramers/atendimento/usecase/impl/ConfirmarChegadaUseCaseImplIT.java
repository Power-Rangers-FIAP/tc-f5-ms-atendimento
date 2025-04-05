package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class ConfirmarChegadaUseCaseImplIT {

    @InjectMocks
    private ConfirmarChegadaUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Mock
    private ControleAtendimentoGateway controleAtendimentoGateway;

    @Test
    void deveConfirmarChegadaIntegrandoDependencias() {
        String idAtendimento = "1";
        Atendimento atendimentoMock = new Atendimento();

        when(atendimentoGateway.getById(idAtendimento)).thenReturn(atendimentoMock);

        assertDoesNotThrow(() -> useCase.execute(idAtendimento));

        verify(atendimentoGateway).save(atendimentoMock);
        verify(controleAtendimentoGateway).atualizarQuantidadePacientes(any(), eq(1));
    }
}
