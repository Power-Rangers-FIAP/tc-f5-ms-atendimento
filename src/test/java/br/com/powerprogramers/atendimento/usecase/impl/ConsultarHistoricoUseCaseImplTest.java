package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultarHistoricoUseCaseImplTest {

    private ConsultarHistoricoUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new ConsultarHistoricoUseCaseImpl(atendimentoGateway);
    }

    @Test
    void deveConsultarHistoricoComSucessoQuandoChaveDePesquisaForValida() {
        ConsultarHistorico input = mock(ConsultarHistorico.class);
        when(input.chavePesquisaValida()).thenReturn(true);

        Atendimento atendimentoMock = mock(Atendimento.class);

        Page<Atendimento> pageMock = new PageImpl<>(
                List.of(atendimentoMock),
                Pageable.ofSize(10),
                1
        );

        when(atendimentoGateway .consultarHistorico(input))
                .thenReturn(pageMock);

        Paginacao<Atendimento> resultado = useCase.execute(input);

        assertNotNull(resultado);
        assertEquals(1, resultado.items().size());
        verify(atendimentoGateway).consultarHistorico(input);
    }

    @Test
    void deveLancarExcecaoQuandoChaveDePesquisaNaoForValida() {
        ConsultarHistorico input = mock(ConsultarHistorico.class);
        when(input.chavePesquisaValida()).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> useCase.execute(input));

        assertNotNull(exception);
        verify(atendimentoGateway, never()).consultarHistorico(any());
    }

    @Test
    void deveLancarExcecaoQuandoGatewayRetornaNulo() {
        ConsultarHistorico input = mock(ConsultarHistorico.class);
        when(input.chavePesquisaValida()).thenReturn(true);
        when(atendimentoGateway.consultarHistorico(input)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> useCase.execute(input));

        assertNotNull(exception);
    }
}
