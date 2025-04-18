package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.EnfermidadeRequest;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;
import br.com.powerprogramers.atendimento.exception.JaPossuiRegistroAtendimentoEmAbertoException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RegistrarEnfermidadeUseCaseImplTest {

    private RegistrarEnfermidadeUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Mock
    private ControleAtendimentoGateway controleAtendimentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new RegistrarEnfermidadeUseCaseImpl(atendimentoGateway, controleAtendimentoGateway);
    }

    @Test
    void deveRegistrarEnfermidadeComSucesso() {
        // Arrange
        String idPaciente = "paciente1";
        String idUnidade = "unidade1";

        // Configuração do input com enfermidades válidas
        RegistrarAtendimento registrarAtendimento = new RegistrarAtendimento(
                idUnidade,
                idPaciente,
                List.of(new EnfermidadeRequest("1","Febre"), new EnfermidadeRequest("2", "Dor de Cabeça"))
        );

        // Atendimentos abertos não existem para o paciente
        when(atendimentoGateway.existeAtendimentoAberto(idPaciente)).thenReturn(null);

        // Retornar um número para unidade
        when(controleAtendimentoGateway.buscarNumero(idUnidade)).thenReturn(123);

        // Simula o comportamento de salvar
        Atendimento atendimentoSalvo = Atendimento.iniciarAtendimento(registrarAtendimento);
        atendimentoSalvo.definirNumero(123);
        when(atendimentoGateway.save(any(Atendimento.class))).thenReturn(atendimentoSalvo);

        // Act
        Atendimento atendimentoRegistrado = useCase.execute(registrarAtendimento);

        // Assert
        assertNotNull(atendimentoRegistrado);
        assertEquals("paciente1", atendimentoRegistrado.getIdPaciente());
        assertEquals("unidade1", atendimentoRegistrado.getIdUnidade());
        assertEquals(123, atendimentoRegistrado.getNumero());

        // Verifica se os métodos das dependências foram chamados nos fluxos corretos
        verify(atendimentoGateway).existeAtendimentoAberto(idPaciente);
        verify(controleAtendimentoGateway).buscarNumero(idUnidade);
        verify(atendimentoGateway).save(any(Atendimento.class));
    }

    @Test
    void deveLancarExcecaoQuandoNaoHouverEnfermidades() {
        // Arrange
        RegistrarAtendimento registrarAtendimento = new RegistrarAtendimento("unidade1", "paciente1", List.of());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(registrarAtendimento);
        });

        assertEquals("Nenhuma enfermidade informada", exception.getMessage());

        // Verifica que nenhuma interação foi feita com os gateways
        verifyNoInteractions(atendimentoGateway);
        verifyNoInteractions(controleAtendimentoGateway);
    }

    @Test
    void deveLancarExcecaoQuandoJaExistirAtendimentoAberto() {
        // Arrange
        String idPaciente = "paciente1";

        RegistrarAtendimento registrarAtendimento = new RegistrarAtendimento(
                "unidade1",
                idPaciente,
                List.of(new EnfermidadeRequest("1","Tosse"), new EnfermidadeRequest("2", "Febre"))
        );

        when(atendimentoGateway.existeAtendimentoAberto(idPaciente)).thenReturn(Atendimento.builder().build());

        // Act & Assert
        assertThrows(JaPossuiRegistroAtendimentoEmAbertoException.class, () -> {
            useCase.execute(registrarAtendimento);
        });

        // Verifica que apenas `existeAtendimentoAberto` foi chamado
        verify(atendimentoGateway).existeAtendimentoAberto(idPaciente);
        verifyNoMoreInteractions(atendimentoGateway);
        verifyNoInteractions(controleAtendimentoGateway);
    }
}
