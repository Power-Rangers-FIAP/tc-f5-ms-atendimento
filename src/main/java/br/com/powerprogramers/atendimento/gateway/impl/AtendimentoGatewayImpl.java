package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarAvaliacao;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.exception.AtendimentoNaoEncontradoException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AtendimentoGatewayImpl implements AtendimentoGateway {

  private static final AtendimentoMapper atendimentoMapper = AtendimentoMapper.INSTANCE;
  private final AtendimentoRepository atendimentoRepository;
  private final MongoTemplate mongoTemplate;

  @Override
  public Atendimento getById(String idAtendimento) {
    var atendimentoEntity =
        this.atendimentoRepository
            .findById(idAtendimento)
            .orElseThrow(AtendimentoNaoEncontradoException::new);
    return atendimentoMapper.toDomain(atendimentoEntity);
  }

  @Override
  public Atendimento save(Atendimento atendimento) {
    var atendimentoEntity = atendimentoMapper.toEntity(atendimento);
    atendimentoEntity = this.atendimentoRepository.save(atendimentoEntity);
    return atendimentoMapper.toDomain(atendimentoEntity);
  }

  @Override
  public Atendimento existeAtendimentoAberto(String idPaciente) {
    return this.atendimentoRepository.findByIdPacienteAndStatusNot(
        idPaciente, StatusAtendimento.FINALIZADO);
  }

  @Override
  public Page<Atendimento> consultarHistorico(ConsultarHistorico input) {
    var pageable = PageRequest.of(input.pagina(), input.porPagina());

    Page<AtendimentoEntity> atendimentos;
    if (input.idPaciente() != null) {
      atendimentos =
          this.atendimentoRepository.findByIdPacienteAndStatusNot(
              pageable, input.idPaciente(), StatusAtendimento.ABERTO);
    } else {
      atendimentos =
          this.atendimentoRepository.findByIdMedicoAndStatusNot(
              pageable, input.idMedico(), StatusAtendimento.ABERTO);
    }

    return atendimentos.map(AtendimentoMapper.INSTANCE::toDomain);
  }

  @Override
  public Page<Atendimento> consultarAvaliacao(ConsultarAvaliacao input) {
    var pageable = PageRequest.of(input.pagina(), input.porPagina());

    Criteria criteria = new Criteria();
    if (input.idUnidade() != null) {
      criteria.and("idUnidade").is(input.idUnidade());
    } else {
      criteria.and("idMedico").is(input.idMedico());
    }

    criteria.and("status").is(StatusAtendimento.FINALIZADO);
    criteria.and("avaliacao").ne(null);

    Query query = new Query(criteria).with(pageable);
    List<AtendimentoEntity> atendimentos = mongoTemplate.find(query, AtendimentoEntity.class);
    long count = mongoTemplate.count(query.skip(-1).limit(-1), AtendimentoEntity.class);

    return new PageImpl<>(atendimentos.stream().map(atendimentoMapper::toDomain).toList(), pageable, count);
  }

}
