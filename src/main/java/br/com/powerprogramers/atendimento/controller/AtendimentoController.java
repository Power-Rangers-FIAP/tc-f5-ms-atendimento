package br.com.powerprogramers.atendimento.controller;

import br.com.powerprogramers.atendimento.api.AtendimentoApi;
import br.com.powerprogramers.atendimento.domain.dto.AvaliacaoPaginadaResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.AvaliacaoRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.EnfermidadeResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.FinalizarAtendimentoRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.HistoricoPaginadaResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.LoginRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.RegistrarEnfermidadeRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.RegistrarEnfermidadeResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.TokenResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.UnidadePaginadaResponseDto;
import br.com.powerprogramers.atendimento.domain.enums.Enfermidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Pagina;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import br.com.powerprogramers.atendimento.usecase.AvaliarAtendimentoUseCase;
import br.com.powerprogramers.atendimento.usecase.ConfirmarChegadaUseCase;
import br.com.powerprogramers.atendimento.usecase.ConsultarAvaliacaoUseCase;
import br.com.powerprogramers.atendimento.usecase.ConsultarHistoricoUseCase;
import br.com.powerprogramers.atendimento.usecase.FinalizarAtendimentoUseCase;
import br.com.powerprogramers.atendimento.usecase.IniciarAtendimentoUseCase;
import br.com.powerprogramers.atendimento.usecase.RealizarLoginUseCase;
import br.com.powerprogramers.atendimento.usecase.RegistrarEnfermidadeUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AtendimentoController implements AtendimentoApi {

  private static final AtendimentoMapper atendimentoMapper = AtendimentoMapper.INSTANCE;
  private final RealizarLoginUseCase realizarLoginUseCase;
  private final ConfirmarChegadaUseCase confirmarChegadaUseCase;
  private final AvaliarAtendimentoUseCase avaliarAtendimentoUseCase;
  private final FinalizarAtendimentoUseCase finalizarAtendimentoUseCase;
  private final ConsultarHistoricoUseCase consultarHistoricoUseCase;
  private final IniciarAtendimentoUseCase iniciarAtendimentoUseCase;
  private final RegistrarEnfermidadeUseCase registrarEnfermidadeUseCase;
  private final ConsultarAvaliacaoUseCase consultarAvaliacaoUseCase;

  @Override
  public ResponseEntity<Void> avaliarAtendimento(AvaliacaoRequestDto body) {
    log.info("Avaliando atendimento :: Inicio");
    var request = atendimentoMapper.toDomain(body);
    avaliarAtendimentoUseCase.execute(request);
    log.info("Avaliando atendimento :: Fim");
    return ResponseEntity.accepted().build();
  }

  @Override
  public ResponseEntity<Void> confirmarChegada(String idAtendimento) {
    log.info("Confirmando chegada :: Inicio");
    confirmarChegadaUseCase.execute(idAtendimento);
    log.info("Confirmando chegada :: Fim");
    return ResponseEntity.accepted().build();
  }

  @Override
  public ResponseEntity<List<EnfermidadeResponseDto>> consultarEnfermidade() {
    log.info("Consultando enfermidade :: Inicio");
    var enfermidades = EnumUtils.getEnumList(Enfermidade.class);
    var enfermidadeResponseDtos = enfermidades.stream().map(atendimentoMapper::toDto).toList();
    log.info("Consultando enfermidade :: Fim");
    return ResponseEntity.ok().body(enfermidadeResponseDtos);
  }

  @Override
  public ResponseEntity<HistoricoPaginadaResponseDto> consultarHistorico(
      Integer pagina, Integer porPagina, String idPaciente, String idMedico) {
    log.info("Consultando historico :: Inicio");
    var request = atendimentoMapper.toDomain(pagina, porPagina, idPaciente, idMedico);

    var response = this.consultarHistoricoUseCase.execute(request);

    final var historico =
        new HistoricoPaginadaResponseDto()
            .items(response.items().stream().map(atendimentoMapper::toHistoricoDto).toList())
            .pagina(response.pagina())
            .porPagina(response.porPagina())
            .total(response.total());

    log.info("Consultando historico :: Fim");
    return ResponseEntity.ok().body(historico);
  }

  @Override
  public ResponseEntity<Void> finalizarAtendimento(FinalizarAtendimentoRequestDto body) {
    log.info("Finalizando atendimento :: Inicio");
    var request = atendimentoMapper.toDomain(body);
    finalizarAtendimentoUseCase.execute(request);
    log.info("Finalizando atendimento :: Fim");
    return ResponseEntity.accepted().build();
  }

  @Override
  public ResponseEntity<UnidadePaginadaResponseDto> iniciarAtendimento(
      Integer pagina, Integer porPagina) {
    log.info("Listando unidade para iniciar atendimento :: Inicio");
    var page = new Pagina(pagina, porPagina);
    var response = iniciarAtendimentoUseCase.execute(page);

    final var unidades =
        new UnidadePaginadaResponseDto()
            .items(response.items().stream().map(atendimentoMapper::toDto).toList())
            .pagina(response.pagina())
            .porPagina(response.porPagina())
            .total(response.total());
    log.info("Listando unidade para iniciar atendimento :: Fim");
    return ResponseEntity.ok().body(unidades);
  }

  @Override
  public ResponseEntity<TokenResponseDto> realizaLogin(LoginRequestDto body) {
    log.info("Realizando login :: Inicio");
    var reqeust = atendimentoMapper.toDomain(body);
    var response = realizarLoginUseCase.execute(reqeust);
    var tokenResponseDto = atendimentoMapper.toDto(response);
    log.info("Realizando login :: Fim");
    return ResponseEntity.ok(tokenResponseDto);
  }

  @Override
  public ResponseEntity<RegistrarEnfermidadeResponseDto> registrarEnfermidade(
      RegistrarEnfermidadeRequestDto body) {
    log.info("Registrando enfermidade :: Inicio");
    var request = atendimentoMapper.toDomain(body);
    var response = registrarEnfermidadeUseCase.execute(request);
    log.info("Registrando enfermidade :: Fim");
    return ResponseEntity.ok().body(atendimentoMapper.toDto(response));
  }

  @Override
  public ResponseEntity<AvaliacaoPaginadaResponseDto> consultarAvaliacoes(Integer pagina, Integer porPagina, String idUnidade, String idMedico) {
    log.info("Consultando avaliacao :: Inicio");
    var request = atendimentoMapper.toAvaliacaoDomain(pagina, porPagina, idUnidade, idMedico);

    var response = this.consultarAvaliacaoUseCase.execute(request);

    final var avaliacao =
        new AvaliacaoPaginadaResponseDto()
            .items(response.items().stream().map(atendimentoMapper::toAvaliacaoDto).toList())
            .pagina(response.pagina())
            .porPagina(response.porPagina())
            .total(response.total());

    log.info("Consultando avaliacao :: Fim");
    return ResponseEntity.ok().body(avaliacao);
  }
}
