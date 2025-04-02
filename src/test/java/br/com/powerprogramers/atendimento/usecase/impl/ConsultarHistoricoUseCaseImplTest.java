package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.Historico;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.gateway.HistoricoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultarHistoricoUseCaseImplTest {

    private ConsultarHistoricoUseCaseImpl useCase;

    @Mock
    private HistoricoGateway historicoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new ConsultarHistoricoUseCaseImpl(historicoGateway);
    }

    @Test
    void deveConsultarHistoricoComSucessoQuandoChaveDePesquisaForValida() {
        ConsultarHistorico input = mock(ConsultarHistorico.class);
        when(input.chavePesquisaValida()).thenReturn(true);

        Atendimento atendimentoMock = new Atendimento(
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
        Page<Atendimento> pageMock = new PageImpl<>(
                List.of(atendimentoMock),
                Pageable.ofSize(10),
                1
        );

        when(historicoGateway.consultarHistorico(input))
                .thenReturn(pageMock);

        Paginacao<Historico> resultado = useCase.execute(input);

        assertNotNull(resultado);
        assertEquals(1, resultado.items().size());
        verify(historicoGateway).consultarHistorico(input);
    }

    @Test
    void deveLancarExcecaoQuandoChaveDePesquisaNaoForValida() {
        ConsultarHistorico input = mock(ConsultarHistorico.class);
        when(input.chavePesquisaValida()).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> useCase.execute(input));

        assertNotNull(exception);
        verify(historicoGateway, never()).consultarHistorico(any());
    }

    @Test
    void deveLancarExcecaoQuandoGatewayRetornaNulo() {
        ConsultarHistorico input = mock(ConsultarHistorico.class);
        when(input.chavePesquisaValida()).thenReturn(true);
        when(historicoGateway.consultarHistorico(input)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> useCase.execute(input));

        assertNotNull(exception);
    }
}
