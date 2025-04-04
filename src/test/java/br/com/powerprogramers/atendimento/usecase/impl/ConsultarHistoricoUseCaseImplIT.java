package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultarHistoricoUseCaseImplIT {

    @InjectMocks
    private ConsultarHistoricoUseCaseImpl useCase;

    @Mock
    private AtendimentoGateway atendimentoGateway;

    @Test
    void deveIntegrarComDependenciasESuportarConsulta() {
        ConsultarHistorico input = mock(ConsultarHistorico.class);
        when(input.chavePesquisaValida()).thenReturn(true);

        Atendimento atendimentoMock = new Atendimento();

        Page<Atendimento> pageMock = new PageImpl<>(
                List.of(atendimentoMock),
                Pageable.ofSize(10),
                1
        );

        when(atendimentoGateway.consultarHistorico(input)).thenReturn(pageMock);

        Paginacao<Atendimento> resultado = useCase.execute(input);

        assertNotNull(resultado);
        assertEquals(1, resultado.items().size());
        verify(atendimentoGateway).consultarHistorico(input);
    }
}
