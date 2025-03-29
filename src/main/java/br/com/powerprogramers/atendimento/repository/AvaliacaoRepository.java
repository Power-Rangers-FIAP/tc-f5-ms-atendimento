package br.com.powerprogramers.atendimento.repository;

import br.com.powerprogramers.atendimento.entity.AvaliacaoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvaliacaoRepository extends MongoRepository<AvaliacaoEntity, String> {
}
