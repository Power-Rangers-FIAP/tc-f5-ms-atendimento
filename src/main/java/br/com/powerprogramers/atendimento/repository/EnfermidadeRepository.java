package br.com.powerprogramers.atendimento.repository;

import br.com.powerprogramers.atendimento.entity.RegistroEnfermidadeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnfermidadeRepository extends MongoRepository<RegistroEnfermidadeEntity, Long> {
}
