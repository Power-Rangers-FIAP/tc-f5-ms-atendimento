package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.RegistrarEnfermidadeUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegistrarEnfermidadeUseCaseImplIT {

    @InjectMocks
    private RegistrarEnfermidadeUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Test
    void deveRegistrarEnfermidadeIntegrandoDependencias() {
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