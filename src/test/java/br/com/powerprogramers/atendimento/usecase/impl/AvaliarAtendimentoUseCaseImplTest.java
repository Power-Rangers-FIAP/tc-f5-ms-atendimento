package br.com.powerprogramers.atendimento.usecase.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAvaliacao;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.AvaliarAtendimentoUseCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AvaliarAtendimentoUseCaseImplTest {

    private AutoCloseable openMocks;
    private AvaliarAtendimentoUseCase useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new AvaliarAtendimentoUseCaseImpl(atendimentoGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveExecutarComSucessoQuandoAtendimentoForFinalizado() {
        Atendimento atendimentoMock = new Atendimento();
        atendimentoMock.setIdPaciente("1");
        atendimentoMock.setStatus(StatusAtendimento.FINALIZADO);

        RegistrarAvaliacao registrarAvaliacao = new RegistrarAvaliacao("1", "1", 5, "Muito bom!");

        when(atendimentoGateway.getById("1")).thenReturn(atendimentoMock);

        assertDoesNotThrow(() -> useCase.execute(registrarAvaliacao));

        verify(atendimentoGateway).save(any(Atendimento.class));
    }

    @Test
    void deveLancarExcecaoQuandoAtendimentoNaoForFinalizado() {
        Atendimento atendimentoMock = new Atendimento();
        atendimentoMock.setStatus(StatusAtendimento.EM_ATENDIMENTO);

        RegistrarAvaliacao registrarAvaliacao = new RegistrarAvaliacao("1","1", 5, "Muito bom!");

        when(atendimentoGateway.getById("1")).thenReturn(atendimentoMock);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> useCase.execute(registrarAvaliacao));

        verify(atendimentoGateway, never()).save(any(Atendimento.class));
        assertNotNull(exception);
    }

}

