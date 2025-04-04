package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.service.UnidadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.reset;

@ExtendWith(MockitoExtension.class)
class UnidadeGatewayImplIT {

    @InjectMocks
    private UnidadeGatewayImpl unidadeGateway;

    @Mock
    private UnidadeService unidadeService;

    @BeforeEach
    void setUp() {
        reset(unidadeService);
    }

    @Test
    void deveExecutarListarUnidadeComSucesso() {
        Pageable pageable = PageRequest.of(0, 10);

        Paginacao<Unidade> resultado = unidadeGateway.listarUnidade(pageable);

//        assertNotNull(resultado);
    }
}
