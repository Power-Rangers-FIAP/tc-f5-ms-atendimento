package br.com.powerprogramers.atendimento.repository;

import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AtendimentoRepository extends MongoRepository<AtendimentoEntity, String> {

    Page<AtendimentoEntity> findByPacienteId(Pageable pageable, String idPaciente);

    Page<AtendimentoEntity> findByMedicoId(Pageable pageable, String medicoId);

}
