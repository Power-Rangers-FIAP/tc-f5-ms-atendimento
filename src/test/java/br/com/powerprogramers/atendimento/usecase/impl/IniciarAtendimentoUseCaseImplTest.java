package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.paginacao.Pagina;
import br.com.powerprogramers.atendimento.gateway.UnidadeGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.*;

class IniciarAtendimentoUseCaseImplTest {

    private IniciarAtendimentoUseCaseImpl useCase;

    @Mock
    private UnidadeGateway unidadeGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new IniciarAtendimentoUseCaseImpl(unidadeGateway);
    }

    @Test
    void deveListarUnidadesComSucesso() {
        Pagina pagina = new Pagina(0, 10);
        PageRequest pageable = PageRequest.of(pagina.pagina(), pagina.porPagina());

        useCase.execute(pagina);

        verify(unidadeGateway).listarUnidade(pageable);
    }
}