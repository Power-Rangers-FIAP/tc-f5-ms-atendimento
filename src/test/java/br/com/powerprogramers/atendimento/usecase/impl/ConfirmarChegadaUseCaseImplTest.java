package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ConfirmarChegadaUseCaseImplTest {

    private ConfirmarChegadaUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new ConfirmarChegadaUseCaseImpl(atendimentoGateway);
    }

    @Test
    void deveConfirmarChegadaComSucesso() {
        String idAtendimento = "123";

        useCase.execute(idAtendimento);

        verify(atendimentoGateway).confirmarChegada(idAtendimento);
    }
}
