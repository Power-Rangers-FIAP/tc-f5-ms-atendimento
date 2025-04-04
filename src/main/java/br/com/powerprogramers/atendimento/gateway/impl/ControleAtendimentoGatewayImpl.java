package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.ControleAtendimento;
import br.com.powerprogramers.atendimento.entity.ControleAtendimentoEntity;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import br.com.powerprogramers.atendimento.repository.ControleAtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ControleAtendimentoGatewayImpl implements ControleAtendimentoGateway {

  private static final AtendimentoMapper atendimentoMapper = AtendimentoMapper.INSTANCE;
  private final ControleAtendimentoRepository controleAtendimentoRepository;
  private final MongoTemplate mongoTemplate;

  @Override
  public Integer buscarNumero(String idUnidade) {
    var numero = 0;

    var controleAtendimento = encontrarControleAtendimento(idUnidade);
    if (controleAtendimento == null) {
      controleAtendimento = ControleAtendimento.criarNovo(idUnidade);
    }

    numero = controleAtendimento.incrementarNumeroGerado();

    var et = atendimentoMapper.toEntity(controleAtendimento);
    this.controleAtendimentoRepository.save(et);

    return numero;
  }

  public void atualizarQuantidadePacientes(String idUnidade, Integer quantidadePacientes) {
    var controleAtendimento = encontrarControleAtendimento(idUnidade);
    if (controleAtendimento != null) {
      controleAtendimento.atualizarQuantidadePacientes(quantidadePacientes);
      this.controleAtendimentoRepository.save(atendimentoMapper.toEntity(controleAtendimento));
    }
  }

  private ControleAtendimento encontrarControleAtendimento(String idUnidade) {
    var query = new Query(Criteria.where("_id").is(idUnidade));
    query.collation(Collation.of("pt").strength(Collation.ComparisonLevel.primary()));
    var controleAtendimentoEntity = mongoTemplate.findOne(query, ControleAtendimentoEntity.class);
    return atendimentoMapper.toDomain(controleAtendimentoEntity);
  }
}
