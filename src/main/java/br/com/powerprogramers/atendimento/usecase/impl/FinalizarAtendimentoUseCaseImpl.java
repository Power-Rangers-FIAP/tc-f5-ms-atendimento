package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.exception.AtendimentoJaFinalizadoException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.FinalizarAtendimentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinalizarAtendimentoUseCaseImpl implements FinalizarAtendimentoUseCase {

  private final AtendimentoGateway atendimentoGateway;
  private final ControleAtendimentoGateway controleAtendimentoGateway;

  @Override
  public void execute(FinalizarAtendimento input) {

    var atendimento = this.atendimentoGateway.getById(input.idAtendimento());

    if (atendimento.getStatus() == StatusAtendimento.FINALIZADO) {
      throw new AtendimentoJaFinalizadoException();
    }

    atendimento.finalizarAtendimento(input.idMedico(), input.comentario());

    this.controleAtendimentoGateway.atualizarQuantidadePacientes(atendimento.getIdUnidade(), -1);

    this.atendimentoGateway.save(atendimento);
  }
}
