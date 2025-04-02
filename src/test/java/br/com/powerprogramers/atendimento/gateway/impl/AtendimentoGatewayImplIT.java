package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.entity.AvaliacaoEntity;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import br.com.powerprogramers.atendimento.repository.AvaliacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtendimentoGatewayImplIT {

    @InjectMocks
    private AtendimentoGatewayImpl atendimentoGateway;

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    void setUp() {
        reset(atendimentoRepository, avaliacaoRepository);
    }

    @Test
    void deveConfirmarFluxoCompletoDeRegistroDeAtendimento() {
        RegistrarAtendimento registrarAtendimento = new RegistrarAtendimento("unidade1", "paciente1", null);

        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();
        when(atendimentoRepository.save(any())).thenReturn(atendimentoEntity);

        Atendimento result = atendimentoGateway.registrarEnfermidade(registrarAtendimento);

        assertNotNull(result);
        verify(atendimentoRepository, times(1)).save(any());
    }

    @Test
    void deveConfirmarFluxoCompletoDeFinalizacaoDeAtendimento() {
        String idAtendimento = "123";
        FinalizarAtendimento finalizarAtendimento = new FinalizarAtendimento(idAtendimento, "medico123", "Comentário");
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();

        when(atendimentoRepository.findById(idAtendimento)).thenReturn(Optional.of(atendimentoEntity));

        atendimentoGateway.finalizarAtendimento(finalizarAtendimento);

        verify(atendimentoRepository, times(1)).save(atendimentoEntity);
    }

    @Test
    void deveConfirmarFluxoCompletoDeConfirmacaoDeChegada() {
        String idAtendimento = "123";
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();
        when(atendimentoRepository.findById(idAtendimento)).thenReturn(Optional.of(atendimentoEntity));

        atendimentoGateway.confirmarChegada(idAtendimento);

        verify(atendimentoRepository, times(1)).save(atendimentoEntity);
    }

    @Test
    void deveConfirmarFluxoCompletoDeAvaliacao() {
        AvaliacaoEntity avaliacao = new AvaliacaoEntity();
        atendimentoGateway.save(avaliacao);
        verify(avaliacaoRepository, times(1)).save(avaliacao);
    }
}