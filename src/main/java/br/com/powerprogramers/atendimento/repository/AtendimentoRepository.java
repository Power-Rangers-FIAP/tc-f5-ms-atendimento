package br.com.powerprogramers.atendimento.repository;

import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AtendimentoRepository extends MongoRepository<AtendimentoEntity, String> {

  Page<AtendimentoEntity> findByIdPacienteAndStatusNot(
      Pageable pageable, String idPaciente, StatusAtendimento status);

  Page<AtendimentoEntity> findByIdMedicoAndStatusNot(
      Pageable pageable, String medicoId, StatusAtendimento status);

  boolean existsByIdPacienteAndStatusNot(String pacienteId, StatusAtendimento status);

  @Query("{ 'status' : ?0 }")
  List<AtendimentoEntity> findByStatus(StatusAtendimento status);

}
