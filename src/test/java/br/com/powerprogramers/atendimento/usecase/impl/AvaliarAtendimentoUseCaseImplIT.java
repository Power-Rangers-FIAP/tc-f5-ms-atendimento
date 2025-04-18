package br.com.powerprogramers.atendimento.usecase.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAvaliacao;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AvaliarAtendimentoUseCaseImplIT {

    @InjectMocks
    private AvaliarAtendimentoUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Test
    void deveSalvarAvaliacaoCompondoTodasAsDependencias() {
        Atendimento atendimentoMock = new Atendimento();
        atendimentoMock.setIdPaciente("1");
        atendimentoMock.setStatus(StatusAtendimento.FINALIZADO);

        RegistrarAvaliacao registrarAvaliacao = new RegistrarAvaliacao("1", "1", 4, "Serviço muito bom.");

        when(atendimentoGateway.getById("1")).thenReturn(atendimentoMock);

        assertDoesNotThrow(() -> useCase.execute(registrarAvaliacao));
        verify(atendimentoGateway).save(any());
    }
}
