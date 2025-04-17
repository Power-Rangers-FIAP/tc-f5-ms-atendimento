package br.com.powerprogramers.atendimento.controller;

import br.com.powerprogramers.atendimento.domain.Role;
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
import br.com.powerprogramers.atendimento.domain.dto.UserInfoDTO;
import br.com.powerprogramers.atendimento.domain.enums.Enfermidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Pagina;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import br.com.powerprogramers.atendimento.usecase.AvaliarAtendimentoUseCase;
import br.com.powerprogramers.atendimento.usecase.ConfirmarChegadaUseCase;
import br.com.powerprogramers.atendimento.usecase.ConsultarAtendimentoUseCase;
import br.com.powerprogramers.atendimento.usecase.ConsultarAvaliacaoUseCase;
import br.com.powerprogramers.atendimento.usecase.ConsultarHistoricoUseCase;
import br.com.powerprogramers.atendimento.usecase.FinalizarAtendimentoUseCase;
import br.com.powerprogramers.atendimento.usecase.IniciarAtendimentoUseCase;
import br.com.powerprogramers.atendimento.usecase.RealizarLoginUseCase;
import br.com.powerprogramers.atendimento.usecase.RegistrarEnfermidadeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("atendimento")
@RequiredArgsConstructor
public class AtendimentoController {
  //FIXME validar dados dos objetos.
  private static final AtendimentoMapper atendimentoMapper = AtendimentoMapper.INSTANCE;
  private final RealizarLoginUseCase realizarLoginUseCase;
  private final ConfirmarChegadaUseCase confirmarChegadaUseCase;
  private final AvaliarAtendimentoUseCase avaliarAtendimentoUseCase;
  private final FinalizarAtendimentoUseCase finalizarAtendimentoUseCase;
  private final ConsultarHistoricoUseCase consultarHistoricoUseCase;
  private final IniciarAtendimentoUseCase iniciarAtendimentoUseCase;
  private final RegistrarEnfermidadeUseCase registrarEnfermidadeUseCase;
  private final ConsultarAvaliacaoUseCase consultarAvaliacaoUseCase;
  private final ConsultarAtendimentoUseCase consultarAtendimentoUseCase;

  @PreAuthorize("hasAnyRole('" + Role.PATIENT +"')")
  @Operation(summary = "Avaliar o atendimento", description = "Paciente realiza a avaliação do atendimento", tags={ "Avaliacao" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "202", description = "finalizado"),
          @ApiResponse(responseCode = "401", description = "Não autorizado") })
  @PostMapping(value = "/avaliacao",
          consumes = { MediaType.APPLICATION_JSON_VALUE },
          produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<Void> avaliarAtendimento(
          @Parameter(hidden = true) @ModelAttribute UserInfoDTO userInfo,
          @Parameter(in = ParameterIn.DEFAULT, description = "Dados da avaliação", schema=@Schema()) @Valid @RequestBody AvaliacaoRequestDto body) {
    log.info("Avaliando atendimento :: Inicio");
    var request = atendimentoMapper.toDomain(body, userInfo);
    avaliarAtendimentoUseCase.execute(request);
    log.info("Avaliando atendimento :: Fim");
    return ResponseEntity.accepted().build();
  }

  @PreAuthorize("hasAnyRole('" + Role.PATIENT +"')")
  @Operation(summary = "Confirmar chegada na unidade", description = "confirma qeu chegou na unidade e vai ser atendido pelo médico", tags={ "Atendimento" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "202", description = "confirmação de chegada"),

          @ApiResponse(responseCode = "401", description = "Não autorizado") })
  @GetMapping(value = "/confirmar/chegada")
  public ResponseEntity<Void> confirmarChegada(@NotNull @Parameter(in = ParameterIn.QUERY, description = "ID do atendimento" ,required=true,schema=@Schema()) @Valid @RequestParam(value = "idAtendimento", required = true) String idAtendimento) {
    log.info("Confirmando chegada :: Inicio");
    confirmarChegadaUseCase.execute(idAtendimento);
    log.info("Confirmando chegada :: Fim");
    return ResponseEntity.accepted().build();
  }

  @Operation(summary = "Consulta enfermidades", description = "Consulta os tipos de enfermidades", tags={ "Atendimento" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Operação bem-sucedida", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EnfermidadeResponseDto.class)))),

          @ApiResponse(responseCode = "401", description = "Não autorizado") })
  @GetMapping(value = "/enfermidade",
          produces = { MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<EnfermidadeResponseDto>> consultarEnfermidade() {
    log.info("Consultando enfermidade :: Inicio");
    var enfermidades = EnumUtils.getEnumList(Enfermidade.class);
    var enfermidadeResponseDtos = enfermidades.stream().map(atendimentoMapper::toDto).toList();
    log.info("Consultando enfermidade :: Fim");
    return ResponseEntity.ok().body(enfermidadeResponseDtos);
  }

  @PreAuthorize("hasAnyRole('" + Role.PATIENT +"','" + Role.DOCTOR +"')")
  @Operation(summary = "Realiza consulta do historico", description = "Retorna o historico do paciente ou histórico de atendimento do médico", tags={ "Historico" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Operação bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HistoricoPaginadaResponseDto.class))),
          @ApiResponse(responseCode = "400", description = "Requisição mal sucedida"),
          @ApiResponse(responseCode = "401", description = "Não autorizado") })
  @GetMapping(value = "/historico",
          produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<HistoricoPaginadaResponseDto> consultarHistorico(@Parameter(in = ParameterIn.QUERY, description = "Número da página" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "pagina", required = false, defaultValue="0") Integer pagina
          , @Parameter(in = ParameterIn.QUERY, description = "Número de itens por página" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "porPagina", required = false, defaultValue="10") Integer porPagina
          , @Parameter(in = ParameterIn.QUERY, description = "ID do paciente" ,schema=@Schema()) @Valid @RequestParam(value = "idPaciente", required = false) String idPaciente
          , @Parameter(in = ParameterIn.QUERY, description = "ID do médico" ,schema=@Schema()) @Valid @RequestParam(value = "idMedico", required = false) String idMedico) {
    log.info("Consultando historico :: Inicio");
    var request = atendimentoMapper.toDomain(pagina, porPagina, idPaciente, idMedico);

    var response = this.consultarHistoricoUseCase.execute(request);

    final var historico =
        HistoricoPaginadaResponseDto.builder()
            .items(response.items().stream().map(atendimentoMapper::toHistoricoDto).toList())
            .pagina(response.pagina())
            .porPagina(response.porPagina())
            .total(response.total())
            .build();

    log.info("Consultando historico :: Fim");
    return ResponseEntity.ok().body(historico);
  }

  @PreAuthorize("hasAnyRole('" + Role.DOCTOR +"')")
  @Operation(summary = "Finaliza o atendimento", description = "finaliza o atendimento do paciente finalizando sua ficha e gerando o historico", tags={ "Atendimento" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "202", description = "finalizado"),
          @ApiResponse(responseCode = "401", description = "Não autorizado") })
  @PostMapping(value = "/finalizar",
          consumes = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<Void> finalizarAtendimento(
          @Parameter(hidden = true) @ModelAttribute UserInfoDTO userInfo,
          @Parameter(in = ParameterIn.DEFAULT, description = "Credenciais do medido e do atendimento", schema=@Schema()) @Valid @RequestBody FinalizarAtendimentoRequestDto body) {
    log.info("Finalizando atendimento :: Inicio");
    var request = atendimentoMapper.toDomain(body, userInfo);
    finalizarAtendimentoUseCase.execute(request);
    log.info("Finalizando atendimento :: Fim");
    return ResponseEntity.accepted().build();
  }

  @PreAuthorize("hasAnyRole('" + Role.PATIENT +"')")
  @Operation(summary = "Inicia o atendimento", description = "Inicia o processo de atendimento", tags={ "Atendimento" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Operação bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnidadePaginadaResponseDto.class))),

          @ApiResponse(responseCode = "401", description = "Não autorizado") })
  @GetMapping(value = "/iniciar",
          produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<UnidadePaginadaResponseDto> iniciarAtendimento(
          @Parameter(in = ParameterIn.QUERY, description = "Número da página" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "pagina", required = false, defaultValue="0") Integer pagina
          , @Parameter(in = ParameterIn.QUERY, description = "Número de itens por página" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "porPagina", required = false, defaultValue="10") Integer porPagina) {
    log.info("Listando unidade para iniciar atendimento :: Inicio");
    var page = new Pagina(pagina, porPagina);
    var response = iniciarAtendimentoUseCase.execute(page);

    final var unidades =
        UnidadePaginadaResponseDto.builder()
            .items(response.items().stream().map(atendimentoMapper::toDto).toList())
            .pagina(response.pagina())
            .porPagina(response.porPagina())
            .total(response.total())
            .build();
    log.info("Listando unidade para iniciar atendimento :: Fim");
    return ResponseEntity.ok().body(unidades);
  }

  @Operation(summary = "Realiza o login", description = "Dado as credenciais de um usuario, retorna o token de acesso", tags={ "Atendimento" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Operação bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponseDto.class))),

          @ApiResponse(responseCode = "401", description = "Não autorizado") })
  @PostMapping(value = "/login",
          produces = { MediaType.APPLICATION_JSON_VALUE },
          consumes = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<TokenResponseDto> realizaLogin(@Parameter(in = ParameterIn.DEFAULT, description = "Credenciais do usuario", required=true, schema=@Schema()) @Valid @RequestBody LoginRequestDto body) {
    log.info("Realizando login :: Inicio");
    var request = atendimentoMapper.toDomain(body);
    var response = realizarLoginUseCase.execute(request);
    var tokenResponseDto = atendimentoMapper.toDto(response);
    log.info("Realizando login :: Fim");
    return ResponseEntity.ok(tokenResponseDto);
  }

  @PreAuthorize("hasAnyRole('" + Role.PATIENT +"')")
  @Operation(summary = "Registra enfermidade do paciente", description = "Registra a enfermidade do paciente para dar inicio ao processo de triagem", tags={ "Atendimento" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Operação bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrarEnfermidadeResponseDto.class))),

          @ApiResponse(responseCode = "401", description = "Não autorizado") })
  @PostMapping(value = "/registrar",
          consumes = { MediaType.APPLICATION_JSON_VALUE },
          produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<RegistrarEnfermidadeResponseDto> registrarEnfermidade(
          @Parameter(hidden = true) @ModelAttribute UserInfoDTO userInfo,
          @Parameter(in = ParameterIn.DEFAULT, description = "Credenciais do usuario", required=true, schema=@Schema()) @Valid @RequestBody RegistrarEnfermidadeRequestDto body) {
    log.info("Registrando enfermidade :: Inicio");
    var request = atendimentoMapper.toDomain(body, userInfo.getUserId());
    var response = registrarEnfermidadeUseCase.execute(request);
    log.info("Registrando enfermidade :: Fim");
    return ResponseEntity.ok().body(atendimentoMapper.toDto(response));
  }

  @Operation(summary = "Realiza consulta de avaliação", description = "Retorna as avaliações de atendimento por medico ou unidade", tags={ "Avaliacao" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Operação bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AvaliacaoPaginadaResponseDto.class))),
          @ApiResponse(responseCode = "400", description = "Requisição mal sucedida"),
          @ApiResponse(responseCode = "401", description = "Não autorizado") })
  @GetMapping(value = "/avaliacao",
          produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<AvaliacaoPaginadaResponseDto> consultarAvaliacoes(@Parameter(in = ParameterIn.QUERY, description = "Número da página" ,schema=@Schema( defaultValue="0")) @Valid @RequestParam(value = "pagina", required = false, defaultValue="0") Integer pagina
          , @Parameter(in = ParameterIn.QUERY, description = "Número de itens por página" ,schema=@Schema( defaultValue="10")) @Valid @RequestParam(value = "porPagina", required = false, defaultValue="10") Integer porPagina
          , @Parameter(in = ParameterIn.QUERY, description = "ID da unidade" ,schema=@Schema()) @Valid @RequestParam(value = "idUnidade", required = false) String idUnidade
          , @Parameter(in = ParameterIn.QUERY, description = "ID do médico" ,schema=@Schema()) @Valid @RequestParam(value = "idMedico", required = false) String idMedico) {
    log.info("Consultando avaliacao :: Inicio");
    var request = atendimentoMapper.toAvaliacaoDomain(pagina, porPagina, idUnidade, idMedico);

    var response = this.consultarAvaliacaoUseCase.execute(request);

    final var avaliacao =
        AvaliacaoPaginadaResponseDto.builder()
            .items(response.items().stream().map(atendimentoMapper::toAvaliacaoDto).toList())
            .pagina(response.pagina())
            .porPagina(response.porPagina())
            .total(response.total())
            .build();

    log.info("Consultando avaliacao :: Fim");
    return ResponseEntity.ok().body(avaliacao);
  }

  @PreAuthorize("hasAnyRole('" + Role.PATIENT +"')")
  @Operation(summary = "Consulta atendimento em aberto", description = "Colsuta o atendimento em aberto do paciente", tags={ "Atendimento" })
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Operação bem-sucedida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrarEnfermidadeResponseDto.class))),
          @ApiResponse(responseCode = "401", description = "Não autorizado") })
  @GetMapping(
          produces = { MediaType.APPLICATION_JSON_VALUE })
  public ResponseEntity<RegistrarEnfermidadeResponseDto> consultaAtendimento(
          @Parameter(hidden = true) @ModelAttribute UserInfoDTO userInfo) {
    log.info("Consultando atendimento :: Inicio");
    var request = userInfo.getUserId();
    var response = consultarAtendimentoUseCase.execute(request);
    log.info("Consultando atendimento :: Fim");
    return ResponseEntity.ok().body(atendimentoMapper.toDto(response));
  }
}
