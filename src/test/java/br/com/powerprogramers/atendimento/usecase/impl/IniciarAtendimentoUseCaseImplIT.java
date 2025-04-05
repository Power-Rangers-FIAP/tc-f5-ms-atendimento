package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.paginacao.Pagina;
import br.com.powerprogramers.atendimento.gateway.UnidadeGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IniciarAtendimentoUseCaseImplIT {

    @InjectMocks
    private IniciarAtendimentoUseCaseImpl useCase;

    @Mock
    private UnidadeGateway unidadeGateway;

    @Test
    void deveListarUnidadesIntegrandoDependencias() {
        Pagina pagina = new Pagina(0, 10);
        PageRequest pageable = PageRequest.of(pagina.pagina(), pagina.porPagina());

        useCase.execute(pagina);

        verify(unidadeGateway).listarUnidade(pageable);
    }
}