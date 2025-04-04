package br.com.powerprogramers.atendimento.repository;

import br.com.powerprogramers.atendimento.entity.ControleAtendimentoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ControleAtendimentoRepository
    extends MongoRepository<ControleAtendimentoEntity, String> {}
