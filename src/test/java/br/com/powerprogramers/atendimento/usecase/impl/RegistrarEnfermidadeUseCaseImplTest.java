package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RegistrarEnfermidadeUseCaseImplTest {

    private RegistrarEnfermidadeUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new RegistrarEnfermidadeUseCaseImpl(atendimentoGateway);
    }

    @Test
    void deveRegistrarEnfermidadeComSucesso() {
        RegistrarAtendimento registrarAtendimento = new RegistrarAtendimento("unidade1", "paciente1", null);
        Atendimento atendimentoEsperado = new Atendimento(
                "12345",
                "paciente1",
                "medico1",
                "unidade1",
                LocalDateTime.of(2023, 10, 10, 14, 0),
                LocalDateTime.of(2023, 10, 10, 15, 0),
                StatusAtendimento.FINALIZADO,
                null,
                "Nenhum problema identificável",
                1

        );

        when(atendimentoGateway.registrarEnfermidade(registrarAtendimento)).thenReturn(atendimentoEsperado);

        Atendimento atendimentoRetornado = useCase.execute(registrarAtendimento);

        assertEquals(atendimentoEsperado, atendimentoRetornado);
        verify(atendimentoGateway).registrarEnfermidade(registrarAtendimento);
    }
}