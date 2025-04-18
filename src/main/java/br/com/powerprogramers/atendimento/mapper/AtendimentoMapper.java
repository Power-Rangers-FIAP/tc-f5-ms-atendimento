package br.com.powerprogramers.atendimento.mapper;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarAvaliacao;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.ControleAtendimento;
import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAvaliacao;
import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.dto.AvaliacaoRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.AvaliacaoResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.EnfermidadeResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.FinalizarAtendimentoRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.HistoricoResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.LoginRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.RegistrarEnfermidadeRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.RegistrarEnfermidadeResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.TokenResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.UnidadeResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.UserInfoDTO;
import br.com.powerprogramers.atendimento.domain.enums.Enfermidade;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.entity.ControleAtendimentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Mapper
public interface AtendimentoMapper {

  AtendimentoMapper INSTANCE = Mappers.getMapper(AtendimentoMapper.class);

  Login toDomain(LoginRequestDto dto);

  @Mapping(target = "idPaciente", expression = "java(userInfoDTO.getUserId())")
  RegistrarAvaliacao toDomain(AvaliacaoRequestDto dto, UserInfoDTO userInfoDTO);

  @Mapping(target = "idMedico", expression = "java(userInfoDTO.getUserId())")
  @Mapping(target = "userType", expression = "java(userInfoDTO.getUserType())")
  FinalizarAtendimento toDomain(FinalizarAtendimentoRequestDto dto, UserInfoDTO userInfoDTO);

  ConsultarHistorico toDomain(int pagina, int porPagina, String idPaciente, String idMedico);

  @Mapping(target = "idPaciente", expression = "java(idPaciente)")
  RegistrarAtendimento toDomain(RegistrarEnfermidadeRequestDto dto, String idPaciente);

  Atendimento toDomain(AtendimentoEntity entity);

  ControleAtendimento toDomain(ControleAtendimentoEntity entity);

  ConsultarAvaliacao toAvaliacaoDomain(int pagina, int porPagina, String idUnidade, String idMedico);

  TokenResponseDto toDto(Token domain);

  @Mapping(target = "id", expression = "java(domain.name())")
  EnfermidadeResponseDto toDto(Enfermidade domain);

  @Mapping(target = "dataHoraInicio", expression = "java(mapOffsetDateTime(domain.getDataHoraInicio()))")
  @Mapping(target = "dataHoraFim", expression = "java(mapOffsetDateTime(domain.getDataHoraFim()))")
  HistoricoResponseDto toHistoricoDto(Atendimento domain);

  UnidadeResponseDto toDto(Unidade domain);

  RegistrarEnfermidadeResponseDto toDto(Atendimento domain);

  @Mapping(target = "nota", expression = "java(domain.getAvaliacao().nota())")
  @Mapping(target = "comentario", expression = "java(domain.getAvaliacao().comentario())")
  AvaliacaoResponseDto toAvaliacaoDto(Atendimento domain);

  AtendimentoEntity toEntity(Atendimento domain);

  ControleAtendimentoEntity toEntity(ControleAtendimento domain);

  default OffsetDateTime mapOffsetDateTime(Instant instant) {
    if (instant == null) {
      return null;
    }
    return OffsetDateTime.ofInstant(instant, ZoneId.systemDefault());
  }
}
