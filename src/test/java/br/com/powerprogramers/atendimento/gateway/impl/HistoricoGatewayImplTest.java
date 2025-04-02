package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HistoricoGatewayImplTest {

    @InjectMocks
    private HistoricoGatewayImpl historicoGateway;

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveConsultarHistoricoPorPaciente() {
        ConsultarHistorico input = new ConsultarHistorico(0, 10, "paciente123", null);
        PageRequest pageable = PageRequest.of(0, 10);
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();
        Page<AtendimentoEntity> pageEntities = new PageImpl<>(List.of(atendimentoEntity));

        when(atendimentoRepository.findByPacienteId(pageable, "paciente123")).thenReturn(pageEntities);

        Page<Atendimento> result = historicoGateway.consultarHistorico(input);

        assertFalse(result.isEmpty());
        verify(atendimentoRepository, times(1)).findByPacienteId(pageable, "paciente123");
    }

    @Test
    void deveConsultarHistoricoPorMedico() {
        ConsultarHistorico input = new ConsultarHistorico(0, 10, null, "medico123");
        PageRequest pageable = PageRequest.of(0, 10);
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();
        Page<AtendimentoEntity> pageEntities = new PageImpl<>(List.of(atendimentoEntity));

        when(atendimentoRepository.findByMedicoId(pageable, "medico123")).thenReturn(pageEntities);

        Page<Atendimento> result = historicoGateway.consultarHistorico(input);

        assertFalse(result.isEmpty());
        verify(atendimentoRepository, times(1)).findByMedicoId(pageable, "medico123");
    }

    @Test
    void deveRetornarPaginaVaziaQuandoNenhumHistoricoForEncontrado() {
        ConsultarHistorico input = new ConsultarHistorico(0, 10, "paciente123", "medico123");
        PageRequest pageable = PageRequest.of(0, 10);
        Page<AtendimentoEntity> pageEntities = Page.empty();

        when(atendimentoRepository.findByPacienteId(pageable, "paciente123")).thenReturn(pageEntities);

        Page<Atendimento> result = historicoGateway.consultarHistorico(input);

        assertTrue(result.isEmpty());
        verify(atendimentoRepository, times(1)).findByPacienteId(pageable, "paciente123");
    }
}