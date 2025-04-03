package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.exception.AtendimentoNaoEncontradoException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AtendimentoGatewayImpl implements AtendimentoGateway {

  private static final AtendimentoMapper atendimentoMapper = AtendimentoMapper.INSTANCE;
  private final AtendimentoRepository atendimentoRepository;

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
  public boolean existeAtendimentoAberto(String idAtendimento) {
    return this.atendimentoRepository.existsByIdPacienteAndStatusNot(
        idAtendimento, StatusAtendimento.FINALIZADO);
  }

  @Override
  public Page<Atendimento> consultarHistorico(ConsultarHistorico input) {
    var pageable = PageRequest.of(input.pagina(), input.porPagina());

    Page<AtendimentoEntity> atedimentos = null;
    if (input.idPaciente() != null) {
      atedimentos =
          this.atendimentoRepository.findByIdPacienteAndStatusNot(
              pageable, input.idPaciente(), StatusAtendimento.ABERTO);
    } else {
      atedimentos =
          this.atendimentoRepository.findByIdMedicoAndStatusNot(
              pageable, input.idMedico(), StatusAtendimento.ABERTO);
    }

    return atedimentos.map(AtendimentoMapper.INSTANCE::toDomain);
  }
}
