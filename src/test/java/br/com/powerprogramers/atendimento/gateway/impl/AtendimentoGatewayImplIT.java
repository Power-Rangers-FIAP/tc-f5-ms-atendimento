package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.exception.AtendimentoNaoEncontradoException;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AtendimentoGatewayImplIT {

    @InjectMocks
    private AtendimentoGatewayImpl atendimentoGateway;

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @BeforeEach
    void setUp() {
        reset(atendimentoRepository);
    }

    @Test
    void deveRetornarAtendimentoPorIdComSucesso() {
        String idAtendimento = "123";
        AtendimentoEntity atendimentoEntityMock = new AtendimentoEntity();

        when(atendimentoRepository.findById(idAtendimento)).thenReturn(Optional.of(atendimentoEntityMock));

        Atendimento result = atendimentoGateway.getById(idAtendimento);

        assertNotNull(result);
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
        Atendimento atendimentoMock = new Atendimento();
        AtendimentoEntity atendimentoEntityMock = new AtendimentoEntity();

        when(atendimentoRepository.save(any(AtendimentoEntity.class))).thenReturn(atendimentoEntityMock);

        Atendimento result = atendimentoGateway.save(atendimentoMock);

        assertNotNull(result);
        verify(atendimentoRepository, times(1)).save(any(AtendimentoEntity.class));
    }

    @Test
    void deveVerificarExistenciaDeAtendimentoEmAberto() {
        String idPaciente = "paciente123";
        when(atendimentoRepository.existsByIdPacienteAndStatusNot(idPaciente, StatusAtendimento.FINALIZADO))
                .thenReturn(true);

        boolean exists = atendimentoGateway.existeAtendimentoAberto(idPaciente);

        assertTrue(exists);
        verify(atendimentoRepository, times(1))
                .existsByIdPacienteAndStatusNot(idPaciente, StatusAtendimento.FINALIZADO);
    }

    @Test
    void deveConsultarHistoricoDeAtendimentosPorPaciente() {
        ConsultarHistorico input = new ConsultarHistorico(0, 5,"paciente1", null);
        AtendimentoEntity atendimentoEntityMock = new AtendimentoEntity();

        PageRequest pageRequest = PageRequest.of(input.pagina(), input.porPagina());
        Page<AtendimentoEntity> pageMock = new PageImpl<>(Collections.singletonList(atendimentoEntityMock), pageRequest, 1);

        when(atendimentoRepository.findByIdPacienteAndStatusNot(
                pageRequest,
                input.idPaciente(),
                StatusAtendimento.ABERTO)
        ).thenReturn(pageMock);

        Page<Atendimento> result = atendimentoGateway.consultarHistorico(input);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(atendimentoRepository, times(1))
                .findByIdPacienteAndStatusNot(pageRequest, input.idPaciente(), StatusAtendimento.ABERTO);
    }

    @Test
    void deveConsultarHistoricoDeAtendimentosPorMedico() {
        ConsultarHistorico input = new ConsultarHistorico(0, 5, null, "medico1");
        AtendimentoEntity atendimentoEntityMock = new AtendimentoEntity();

        PageRequest pageRequest = PageRequest.of(input.pagina(), input.porPagina());
        Page<AtendimentoEntity> pageMock = new PageImpl<>(Collections.singletonList(atendimentoEntityMock), pageRequest, 1);

        when(atendimentoRepository.findByIdMedicoAndStatusNot(
                pageRequest,
                input.idMedico(),
                StatusAtendimento.ABERTO)
        ).thenReturn(pageMock);

        Page<Atendimento> result = atendimentoGateway.consultarHistorico(input);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(atendimentoRepository, times(1))
                .findByIdMedicoAndStatusNot(pageRequest, input.idMedico(), StatusAtendimento.ABERTO);
    }

}