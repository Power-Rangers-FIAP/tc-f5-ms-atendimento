package br.com.powerprogramers.atendimento.mapper;

import br.com.powerprogramers.atendimento.domain.Avaliacao;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.EnfermidadeRequest;
import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.domain.Historico;
import br.com.powerprogramers.atendimento.domain.Login;
import br.com.powerprogramers.atendimento.domain.RegistrarEnfermidade;
import br.com.powerprogramers.atendimento.domain.Token;
import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.dto.AvaliacaoRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.EnfermidadeResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.FinalizarAtendimentoRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.HistoricoResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.LoginRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.RegistrarEnfermidadeRequestDto;
import br.com.powerprogramers.atendimento.domain.dto.RegistrarEnfermidadeResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.TokenResponseDto;
import br.com.powerprogramers.atendimento.domain.dto.UnidadeResponseDto;
import br.com.powerprogramers.atendimento.domain.enums.Enfermidade;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.entity.RegistroEnfermidadeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AtendimentoMapper {

    AtendimentoMapper INSTANCE = Mappers.getMapper(AtendimentoMapper.class);

    Login toDomain(LoginRequestDto dto);

    Avaliacao toDomain(AvaliacaoRequestDto dto);

    FinalizarAtendimento toDomain(FinalizarAtendimentoRequestDto dto);

    ConsultarHistorico toDomain(int pagina, int porPagina, String idPaciente, String idMedico);

    RegistrarEnfermidade toDomain(RegistrarEnfermidadeRequestDto dto);

    Historico toDomain(AtendimentoEntity entity);

    RegistrarEnfermidade toDomain(RegistroEnfermidadeEntity entity);

    TokenResponseDto toDto(Token domain);

    @Mapping(target = "id", expression = "java(domain.name())")
    EnfermidadeResponseDto toDto(Enfermidade domain);

    HistoricoResponseDto toDto(Historico domain);

    UnidadeResponseDto toDto(Unidade domain);

    RegistrarEnfermidadeResponseDto toDto(RegistrarEnfermidade domain);

}
