package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Avaliacao;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvaliarAtendimentoUseCaseImplIT {

    @InjectMocks
    private AvaliarAtendimentoUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Test
    void deveSalvarAvaliacaoCompondoTodasAsDependencias() {
        AtendimentoEntity atendimentoMock = new AtendimentoEntity();
        atendimentoMock.setStatus(StatusAtendimento.FINALIZADO);

        Avaliacao avaliacao = new Avaliacao("1", 4, "Serviço muito bom.");

        when(atendimentoGateway.getById("1")).thenReturn(atendimentoMock);

        assertDoesNotThrow(() -> useCase.execute(avaliacao));
        verify(atendimentoGateway).save(any());
    }
}
