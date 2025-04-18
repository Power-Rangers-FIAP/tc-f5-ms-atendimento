package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.exception.AtendimentoNaoEncontradoException;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtendimentoGatewayImplTest {

    @InjectMocks
    private AtendimentoGatewayImpl atendimentoGateway;

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarAtendimentoPorIdComSucesso() {
        String idAtendimento = "123";
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();
        when(atendimentoRepository.findById(idAtendimento)).thenReturn(Optional.of(atendimentoEntity));

        Atendimento result = atendimentoGateway.getById(idAtendimento);

        org.junit.jupiter.api.Assertions.assertNotNull(result);
        verify(atendimentoRepository, times(1)).findById(idAtendimento);
    }

    @Test
    void deveLancarExcecaoQuandoAtendimentoNaoForEncontrado() {
        String idAtendimento = "124";
        when(atendimentoRepository.findById(idAtendimento)).thenReturn(Optional.empty());

        assertThrows(AtendimentoNaoEncontradoException.class, () -> atendimentoGateway.getById(idAtendimento));
        verify(atendimentoRepository, times(1)).findById(idAtendimento);
    }

    @Test
    void deveSalvarAtendimentoComSucesso() {
        Atendimento atendimento = new Atendimento();
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();

        when(atendimentoRepository.save(any(AtendimentoEntity.class))).thenReturn(atendimentoEntity);

        Atendimento result = atendimentoGateway.save(atendimento);

        org.junit.jupiter.api.Assertions.assertNotNull(result);
        verify(atendimentoRepository, times(1)).save(any(AtendimentoEntity.class));
    }

    @Test
    void deveVerificarExistenciaDeAtendimentoEmAberto() {
        Atendimento atendimento = Atendimento.builder().build();
        String idPaciente = "paciente123";
        when(atendimentoRepository.findByIdPacienteAndStatusNot(idPaciente, StatusAtendimento.FINALIZADO))
                .thenReturn(atendimento);

        var existsAtendimento = atendimentoGateway.existeAtendimentoAberto(idPaciente);

        assertNotNull(existsAtendimento);
        verify(atendimentoRepository, times(1))
                .findByIdPacienteAndStatusNot(idPaciente, StatusAtendimento.FINALIZADO);
    }

    @Test
    void deveConsultarHistoricoComPaciente() {
        ConsultarHistorico input = new ConsultarHistorico(0, 10, "paciente123", null);
        PageRequest pageRequest = PageRequest.of(input.pagina(), input.porPagina());
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();

        Page<AtendimentoEntity> pageMock = new PageImpl<>(Collections.singletonList(atendimentoEntity), pageRequest, 1);

        when(atendimentoRepository.findByIdPacienteAndStatusNot(pageRequest, input.idPaciente(), StatusAtendimento.ABERTO))
                .thenReturn(pageMock);

        Page<Atendimento> result = atendimentoGateway.consultarHistorico(input);

        org.junit.jupiter.api.Assertions.assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(atendimentoRepository, times(1))
                .findByIdPacienteAndStatusNot(pageRequest, input.idPaciente(), StatusAtendimento.ABERTO);
    }

    @Test
    void deveConsultarHistoricoComMedico() {
        ConsultarHistorico input = new ConsultarHistorico(0, 10, null, "medico123");
        PageRequest pageRequest = PageRequest.of(input.pagina(), input.porPagina());
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();

        Page<AtendimentoEntity> pageMock = new PageImpl<>(Collections.singletonList(atendimentoEntity), pageRequest, 1);

        when(atendimentoRepository.findByIdMedicoAndStatusNot(pageRequest, input.idMedico(), StatusAtendimento.ABERTO))
                .thenReturn(pageMock);

        Page<Atendimento> result = atendimentoGateway.consultarHistorico(input);

        org.junit.jupiter.api.Assertions.assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(atendimentoRepository, times(1))
                .findByIdMedicoAndStatusNot(pageRequest, input.idMedico(), StatusAtendimento.ABERTO);
    }
}
