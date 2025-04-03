package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.exception.AtendimentoJaFinalizadoException;
import br.com.powerprogramers.atendimento.exception.JaEstaEmAtendimentoException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.ConfirmarChegadaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmarChegadaUseCaseImpl implements ConfirmarChegadaUseCase {

  private final AtendimentoGateway atendimentoGateway;
  private final ControleAtendimentoGateway controleAtendimentoGateway;

  @Override
  public void execute(String idAtendimento) {

    var atendimento = this.atendimentoGateway.getById(idAtendimento);

    if (atendimento.getStatus() == StatusAtendimento.FINALIZADO) {
      throw new AtendimentoJaFinalizadoException();
    }

    if (atendimento.getStatus() == StatusAtendimento.EM_ATENDIMENTO) {
      throw new JaEstaEmAtendimentoException();
    }

    atendimento.confirmarChegada();

    this.atendimentoGateway.save(atendimento);

    this.controleAtendimentoGateway.atualizarQuantidadePacientes(atendimento.getIdUnidade(), 1);
  }
}
