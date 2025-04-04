package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ConfirmarChegadaUseCaseImplTest {

    private ConfirmarChegadaUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Mock
    private ControleAtendimentoGateway controleAtendimentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new ConfirmarChegadaUseCaseImpl(atendimentoGateway, controleAtendimentoGateway);
    }

    @Test
    void deveConfirmarChegadaComSucesso() {
        String idAtendimento = "123";

        Atendimento atendimentoMock = mock(Atendimento.class);

        when(atendimentoGateway.getById(idAtendimento)).thenReturn(atendimentoMock);

        useCase.execute(idAtendimento);

        verify(atendimentoMock).confirmarChegada();
        verify(atendimentoGateway).save(atendimentoMock);
        verify(controleAtendimentoGateway).atualizarQuantidadePacientes(any(), eq(1));
    }
}
