package br.com.powerprogramers.atendimento.controller;

import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.domain.dto.AvaliacaoRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.EnfermidadeResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.FinalizarAtendimentoRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.LoginRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.TokenResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.UserInfoDTO;
import br.com.powerprogramers.atendimento.domain.enums.Enfermidade;
import br.com.powerprogramers.atendimento.usecase.AvaliarAtendimentoUseCase;
import br.com.powerprogramers.atendimento.usecase.ConfirmarChegadaUseCase;
import br.com.powerprogramers.atendimento.usecase.FinalizarAtendimentoUseCase;
import br.com.powerprogramers.atendimento.usecase.RealizarLoginUseCase;
import org.apache.commons.lang3.EnumUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AtendimentoControllerTest {

    private AutoCloseable openMocks;

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
        this.openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.openMocks.close();
    }

    @Test
    void avaliarAtendimento_DeveRetornarAccepted() {
        UserInfoDTO userInfoDTO = UserInfoDTO.builder().userId("557fa359-564b-426a-b6cf-038753102f4a").build();
        AvaliacaoRequestDto requestDto = AvaliacaoRequestDto.builder().build();
        doNothing().when(avaliarAtendimentoUseCase).execute(any());

        ResponseEntity<Void> response = atendimentoController.avaliarAtendimento(userInfoDTO, requestDto);

        assertEquals(202, response.getStatusCode().value());
        verify(avaliarAtendimentoUseCase, times(1)).execute(any());
    }

    @Test
    void confirmarChegada_DeveRetornarAccepted() {
        String idAtendimento = "123";
        doNothing().when(confirmarChegadaUseCase).execute(idAtendimento);

        ResponseEntity<Void> response = atendimentoController.confirmarChegada(idAtendimento);

        assertEquals(202, response.getStatusCode().value());
        verify(confirmarChegadaUseCase, times(1)).execute(idAtendimento);
    }

    @Test
    void consultarEnfermidade_DeveRetornarListaDeEnfermidades() {
        List<Enfermidade> enfermidades = EnumUtils.getEnumList(Enfermidade.class);

        ResponseEntity<List<EnfermidadeResponseDto>> response = atendimentoController.consultarEnfermidade();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(enfermidades.size(), Objects.requireNonNull(response.getBody()).size());
    }

    @Test
    void finalizarAtendimento_DeveRetornarAccepted() {
        UserInfoDTO userInfoDTO = UserInfoDTO.builder().userId("27b1d80d-387c-47e7-8ef3-4824056d6143").build();
        FinalizarAtendimentoRequestDto requestDto = FinalizarAtendimentoRequestDto.builder().build();
        doNothing().when(finalizarAtendimentoUseCase).execute(any());

        ResponseEntity<Void> response = atendimentoController.finalizarAtendimento(userInfoDTO, requestDto);

        assertEquals(202, response.getStatusCode().value());
        verify(finalizarAtendimentoUseCase, times(1)).execute(any());
    }

    @Test
    void realizaLogin_DeveRetornarTokenResponseDto() {
        LoginRequestDto requestDto = LoginRequestDto.builder().build();
        Token tokenResponseDto = new Token("token123");
        when(realizarLoginUseCase.execute(any())).thenReturn(tokenResponseDto);

        ResponseEntity<TokenResponseDto> response = atendimentoController.realizaLogin(requestDto);

        assertEquals(200, response.getStatusCode().value());
        verify(realizarLoginUseCase, times(1)).execute(any());
    }
}
