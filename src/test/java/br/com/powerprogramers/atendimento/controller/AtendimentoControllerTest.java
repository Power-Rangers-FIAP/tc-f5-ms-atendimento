package br.com.powerprogramers.atendimento.controller;

import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.domain.dto.*;
import br.com.powerprogramers.atendimento.domain.enums.Enfermidade;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import br.com.powerprogramers.atendimento.usecase.*;
import org.apache.commons.lang3.EnumUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AtendimentoControllerTest {

    @InjectMocks
    private AtendimentoController atendimentoController;

    @Mock
    private RealizarLoginUseCase realizarLoginUseCase;

    @Mock
    private ConfirmarChegadaUseCase confirmarChegadaUseCase;

    @Mock
    private AvaliarAtendimentoUseCase avaliarAtendimentoUseCase;

    @Mock
    private FinalizarAtendimentoUseCase finalizarAtendimentoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void avaliarAtendimento_DeveRetornarAccepted() {
        AvaliacaoRequestDto requestDto = new AvaliacaoRequestDto();
        doNothing().when(avaliarAtendimentoUseCase).execute(any());

        ResponseEntity<Void> response = atendimentoController.avaliarAtendimento(requestDto);

        assertEquals(202, response.getStatusCodeValue());
        verify(avaliarAtendimentoUseCase, times(1)).execute(any());
    }

    @Test
    void confirmarChegada_DeveRetornarAccepted() {
        String idAtendimento = "123";
        doNothing().when(confirmarChegadaUseCase).execute(idAtendimento);

        ResponseEntity<Void> response = atendimentoController.confirmarChegada(idAtendimento);

        assertEquals(202, response.getStatusCodeValue());
        verify(confirmarChegadaUseCase, times(1)).execute(idAtendimento);
    }

    @Test
    void consultarEnfermidade_DeveRetornarListaDeEnfermidades() {
        List<Enfermidade> enfermidades = EnumUtils.getEnumList(Enfermidade.class);

        ResponseEntity<List<EnfermidadeResponseDto>> response = atendimentoController.consultarEnfermidade();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(enfermidades.size(), response.getBody().size());
    }

    @Test
    void finalizarAtendimento_DeveRetornarAccepted() {
        FinalizarAtendimentoRequestDto requestDto = new FinalizarAtendimentoRequestDto();
        doNothing().when(finalizarAtendimentoUseCase).execute(any());

        ResponseEntity<Void> response = atendimentoController.finalizarAtendimento(requestDto);

        assertEquals(202, response.getStatusCodeValue());
        verify(finalizarAtendimentoUseCase, times(1)).execute(any());
    }

    @Test
    void realizaLogin_DeveRetornarTokenResponseDto() {
        LoginRequestDto requestDto = new LoginRequestDto();
        Token tokenResponseDto = new Token("token123");
        when(realizarLoginUseCase.execute(any())).thenReturn(tokenResponseDto);

        ResponseEntity<TokenResponseDto> response = atendimentoController.realizaLogin(requestDto);

        assertEquals(200, response.getStatusCodeValue());
        verify(realizarLoginUseCase, times(1)).execute(any());
    }
}
