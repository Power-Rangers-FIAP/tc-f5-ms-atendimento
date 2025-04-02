package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.Historico;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.gateway.HistoricoGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultarHistoricoUseCaseImplIT {

    @InjectMocks
    private ConsultarHistoricoUseCaseImpl useCase;

    @Mock
    private HistoricoGateway historicoGateway;

    @Test
    void deveIntegrarComDependenciasESuportarConsulta() {
        // Arrange
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

        ); // Simulação de um objeto Atendimento
        Page<Atendimento> pageMock = new PageImpl<>(
                List.of(atendimentoMock),
                Pageable.ofSize(10),
                1
        );
        when(historicoGateway.consultarHistorico(input)).thenReturn(pageMock);

        // Act
        Paginacao<Historico> resultado = useCase.execute(input);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.items().size());
        verify(historicoGateway).consultarHistorico(input);
    }
}
