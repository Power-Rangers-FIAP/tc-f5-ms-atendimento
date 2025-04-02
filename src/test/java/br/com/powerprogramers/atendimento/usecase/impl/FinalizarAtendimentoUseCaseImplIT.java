package br.com.powerprogramers.atendimento.usecase.impl;
import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FinalizarAtendimentoUseCaseImplIT {

    @InjectMocks
    private FinalizarAtendimentoUseCaseImpl useCase; // Classe que está sendo testada

    @Mock
    private AtendimentoGateway atendimentoGateway; // Dependência simulada

    @Test
    void deveFinalizarAtendimentoComSucesso() {
        FinalizarAtendimento input = new FinalizarAtendimento("1", "2", "3"); // Cria a simulação dos dados de entrada

        useCase.execute(input); // Executa o caso de uso

        verify(atendimentoGateway, times(1)).finalizarAtendimento(input); // Verifica se o método foi chamado UMA vez
    }
}
