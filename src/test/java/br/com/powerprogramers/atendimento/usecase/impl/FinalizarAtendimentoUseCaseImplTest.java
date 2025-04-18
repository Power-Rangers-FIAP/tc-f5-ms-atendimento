package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class FinalizarAtendimentoUseCaseImplTest {

    private AutoCloseable openMocks;

    @InjectMocks
    private FinalizarAtendimentoUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Mock
    private ControleAtendimentoGateway controleAtendimentoGateway;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new FinalizarAtendimentoUseCaseImpl(atendimentoGateway, controleAtendimentoGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveFinalizarAtendimentoComSucesso() {
        FinalizarAtendimento input = new FinalizarAtendimento("1", "2", "muito bom", "DOCTOR");
        Atendimento atendimentoMock = mock(Atendimento.class);

        when(atendimentoGateway.getById(input.idAtendimento())).thenReturn(atendimentoMock);

        useCase.execute(input);

        verify(atendimentoMock).finalizarAtendimento(input.idMedico(), input.comentario());
        verify(atendimentoGateway).save(atendimentoMock);
        verify(controleAtendimentoGateway).atualizarQuantidadePacientes(any(), eq(-1));
    }
}