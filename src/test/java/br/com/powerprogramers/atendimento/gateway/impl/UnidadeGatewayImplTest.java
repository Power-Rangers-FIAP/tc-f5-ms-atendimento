package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.service.UnidadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UnidadeGatewayImplTest {

    @InjectMocks
    private UnidadeGatewayImpl unidadeGateway;

    @Mock
    private UnidadeService unidadeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarUnidadesComSucesso() {
        Pageable pageable = PageRequest.of(0, 10);
        Paginacao<Unidade> paginacaoEsperada = new Paginacao<>(0, 10, 1, null);

        when(unidadeService.listarUnidade(pageable)).thenReturn(paginacaoEsperada);

        Paginacao<Unidade> resultado = unidadeGateway.listarUnidade(pageable);

        assertEquals(paginacaoEsperada, resultado);
        verify(unidadeService, times(1)).listarUnidade(pageable);
    }
}
