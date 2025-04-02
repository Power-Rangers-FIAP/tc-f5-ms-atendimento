package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class FinalizarAtendimentoUseCaseImplTest {

    @InjectMocks
    private FinalizarAtendimentoUseCaseImpl useCase;

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new FinalizarAtendimentoUseCaseImpl(atendimentoGateway);
    }

    @Test
    void deveFinalizarAtendimentoComSucesso() {
        FinalizarAtendimento input = new FinalizarAtendimento("atendimento123", "medico1", "muito bom");

        useCase.execute(input);

        verify(atendimentoGateway).finalizarAtendimento(input);
    }
}