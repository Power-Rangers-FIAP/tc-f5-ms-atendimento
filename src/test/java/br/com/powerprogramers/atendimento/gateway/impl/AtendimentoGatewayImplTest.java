package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.entity.AvaliacaoEntity;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import br.com.powerprogramers.atendimento.repository.AvaliacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtendimentoGatewayImplTest {

    @InjectMocks
    private AtendimentoGatewayImpl atendimentoGateway;

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarAtendimentoPorIdComSucesso() {
        String idAtendimento = "123";
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();
        when(atendimentoRepository.findById(idAtendimento)).thenReturn(Optional.of(atendimentoEntity));

        AtendimentoEntity result = atendimentoGateway.getById(idAtendimento);

        assertNotNull(result);
        assertEquals(atendimentoEntity, result);
    }

    @Test
    void deveLancarExcecaoQuandoAtendimentoNaoForEncontrado() {
        String idAtendimento = "999";
        when(atendimentoRepository.findById(idAtendimento)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> atendimentoGateway.getById(idAtendimento));
    }

    @Test
    void deveSalvarAvaliacaoComSucesso() {
        AvaliacaoEntity avaliacao = new AvaliacaoEntity();
        atendimentoGateway.save(avaliacao);
        verify(avaliacaoRepository, times(1)).save(avaliacao);
    }

    @Test
    void deveConfirmarChegadaComSucesso() {
        String idAtendimento = "123";
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();
        when(atendimentoRepository.findById(idAtendimento)).thenReturn(Optional.of(atendimentoEntity));

        atendimentoGateway.confirmarChegada(idAtendimento);

        verify(atendimentoRepository, times(1)).save(atendimentoEntity);
    }

    @Test
    void deveFinalizarAtendimentoComSucesso() {
        String idAtendimento = "123";
        FinalizarAtendimento finalizarAtendimento = new FinalizarAtendimento(idAtendimento, "medico123", "Comentário");
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();

        when(atendimentoRepository.findById(idAtendimento)).thenReturn(Optional.of(atendimentoEntity));

        atendimentoGateway.finalizarAtendimento(finalizarAtendimento);

        verify(atendimentoRepository, times(1)).save(atendimentoEntity);
    }

    @Test
    void deveRegistrarEnfermidadeComSucesso() {
        AtendimentoEntity atendimentoEntity = new AtendimentoEntity();
        RegistrarAtendimento registrarAtendimento = new RegistrarAtendimento("unidade1", "paciente1", null);
        Atendimento atendimento = new Atendimento(
                "12345",
                "paciente1",
                "medico1",
                "unidade1",
                LocalDateTime.of(2023, 10, 10, 14, 0),
                LocalDateTime.of(2023, 10, 10, 15, 0),
                StatusAtendimento.FINALIZADO,
                null,
                "Nenhum problema identificável",
                1
        );

        when(atendimentoRepository.save(any())).thenReturn(atendimentoEntity);

        Atendimento result = atendimentoGateway.registrarEnfermidade(registrarAtendimento);

        assertNotNull(result);
        verify(atendimentoRepository, times(1)).save(any());
    }
}