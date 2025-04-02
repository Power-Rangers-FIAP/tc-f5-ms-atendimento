package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Avaliacao;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.entity.AvaliacaoEntity;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AvaliarAtendimentoUseCaseImplTest {

    private AvaliarAtendimentoUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new AvaliarAtendimentoUseCaseImpl(atendimentoGateway);
    }

    @Test
    void deveExecutarComSucessoQuandoAtendimentoForFinalizado() {
        AtendimentoEntity atendimentoMock = new AtendimentoEntity();
        atendimentoMock.setStatus(StatusAtendimento.FINALIZADO);

        Avaliacao avaliacao = new Avaliacao("1", 5, "Muito bom!");

        when(atendimentoGateway.getById("1")).thenReturn(atendimentoMock);

        assertDoesNotThrow(() -> useCase.execute(avaliacao));

        verify(atendimentoGateway).save(any(AvaliacaoEntity.class));
    }

    @Test
    void deveLancarExcecaoQuandoAtendimentoNaoForFinalizado() {
        AtendimentoEntity atendimentoMock = new AtendimentoEntity();
        atendimentoMock.setStatus(StatusAtendimento.EM_ATENDIMENTO);

        Avaliacao avaliacao = new Avaliacao("1", 5, "Muito bom!");

        when(atendimentoGateway.getById("1")).thenReturn(atendimentoMock);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> useCase.execute(avaliacao));

        verify(atendimentoGateway, never()).save(any(AvaliacaoEntity.class));
        assertNotNull(exception);
    }

}

