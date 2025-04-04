//package br.com.powerprogramers.atendimento.gateway.impl;
//
//import br.com.powerprogramers.atendimento.domain.Atendimento;
//import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
//import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
//import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class HistoricoGatewayImplIT {
//
//    @InjectMocks
//    private HistoricoGatewayImpl historicoGateway;
//
//    @Mock
//    private AtendimentoRepository atendimentoRepository;
//
//    @BeforeEach
//    void setUp() {
//        reset(atendimentoRepository);
//    }
//
//    @Test
//    void deveConsultarHistoricoPorPaciente() {
//        // Arrange
//        ConsultarHistorico input = new ConsultarHistorico(0, 10, "paciente1", null);
//        PageRequest pageable = PageRequest.of(input.pagina(), input.porPagina());
//
//        AtendimentoEntity entity = new AtendimentoEntity();
//        entity.setId("123");
//        entity.setPacienteId("paciente1");
//
//        when(atendimentoRepository.findByPacienteId(pageable, input.idPaciente()))
//                .thenReturn(new PageImpl<>(List.of(entity)));
//
//        // Act
//        Page<Atendimento> result = historicoGateway.consultarHistorico(input);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.getTotalElements());
//        verify(atendimentoRepository, times(1)).findByPacienteId(pageable, input.idPaciente());
//    }
//
//    @Test
//    void deveConsultarHistoricoPorMedico() {
//        // Arrange
//        ConsultarHistorico input = new ConsultarHistorico(0, 10, null, "medico1");
//        PageRequest pageable = PageRequest.of(input.pagina(), input.porPagina());
//
//        AtendimentoEntity entity = new AtendimentoEntity();
//        entity.setId("123");
//        entity.setMedicoId("medico1");
//
//        when(atendimentoRepository.findByMedicoId(pageable, input.idMedico()))
//                .thenReturn(new PageImpl<>(List.of(entity)));
//
//        // Act
//        Page<Atendimento> result = historicoGateway.consultarHistorico(input);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(1, result.getTotalElements());
//        verify(atendimentoRepository, times(1)).findByMedicoId(pageable, input.idMedico());
//    }
//}