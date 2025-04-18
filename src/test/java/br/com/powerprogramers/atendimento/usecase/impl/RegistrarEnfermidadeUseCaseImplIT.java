package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.EnfermidadeRequest;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;
import br.com.powerprogramers.atendimento.exception.JaPossuiRegistroAtendimentoEmAbertoException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegistrarEnfermidadeUseCaseImplIT {

    @InjectMocks
    private RegistrarEnfermidadeUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Mock
    private ControleAtendimentoGateway controleAtendimentoGateway;

    @Test
    void deveRegistrarAtendimentoComSucesso() {
        String idPaciente = "paciente1";
        String idUnidade = "unidade1";

        RegistrarAtendimento registrarAtendimento = new RegistrarAtendimento(
                idUnidade,
                idPaciente,
                List.of(new EnfermidadeRequest("1","Febre"), new EnfermidadeRequest("2", "Dor de Cabeça"))
        );

        when(atendimentoGateway.existeAtendimentoAberto(idPaciente)).thenReturn(null);
        when(controleAtendimentoGateway.buscarNumero(idUnidade)).thenReturn(10);

        Atendimento atendimentoEsperado = Atendimento.iniciarAtendimento(registrarAtendimento);
        atendimentoEsperado.definirNumero(10);
        when(atendimentoGateway.save(any(Atendimento.class))).thenReturn(atendimentoEsperado);

        Atendimento atendimentoRetornado = useCase.execute(registrarAtendimento);

        assertNotNull(atendimentoRetornado);
        assertEquals(atendimentoEsperado.getIdPaciente(), atendimentoRetornado.getIdPaciente());
        assertEquals(atendimentoEsperado.getIdUnidade(), atendimentoRetornado.getIdUnidade());
        assertEquals(atendimentoEsperado.getNumero(), atendimentoRetornado.getNumero());

        verify(atendimentoGateway).existeAtendimentoAberto(idPaciente);
        verify(controleAtendimentoGateway).buscarNumero(idUnidade);
        verify(atendimentoGateway).save(any(Atendimento.class));
    }

    @Test
    void deveLancarExcecaoQuandoNaoHouverEnfermidades() {
        RegistrarAtendimento registrarAtendimento = new RegistrarAtendimento("unidade1", "paciente1", List.of());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(registrarAtendimento);
        });

        assertEquals("Nenhuma enfermidade informada", exception.getMessage());

        verifyNoInteractions(atendimentoGateway);
        verifyNoInteractions(controleAtendimentoGateway);
    }

    @Test
    void deveLancarExcecaoQuandoJaExistirAtendimentoAberto() {
        String idPaciente = "paciente1";

        RegistrarAtendimento registrarAtendimento = new RegistrarAtendimento(
                "unidade1",
                idPaciente,
                List.of(new EnfermidadeRequest("1","Febre"), new EnfermidadeRequest("2", "Dor de Cabeça"))
        );

        when(atendimentoGateway.existeAtendimentoAberto(idPaciente)).thenReturn(Atendimento.builder().build());

        assertThrows(JaPossuiRegistroAtendimentoEmAbertoException.class, () -> {
            useCase.execute(registrarAtendimento);
        });

        verify(atendimentoGateway).existeAtendimentoAberto(idPaciente);
        verifyNoMoreInteractions(atendimentoGateway);
        verifyNoInteractions(controleAtendimentoGateway);
    }
}