package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.exception.AtendimentoException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.ConsultarAtendimentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultarAtendimentoUseCaseImpl implements ConsultarAtendimentoUseCase {

  private final AtendimentoGateway atendimentoGateway;

  @Override
  public Atendimento execute(String input) {

    var atendimento = this.atendimentoGateway.existeAtendimentoAberto(input);

    if (atendimento == null) {
      throw new AtendimentoException("Nenhum atendimento em aberto!");
    }

    return atendimento;
  }
}
