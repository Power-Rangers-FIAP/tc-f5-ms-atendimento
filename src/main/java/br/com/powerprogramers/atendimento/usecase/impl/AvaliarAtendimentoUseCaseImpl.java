package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Avaliacao;
import br.com.powerprogramers.atendimento.domain.RegistrarAvaliacao;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.exception.AtendimentoNaoFinalizadoException;
import br.com.powerprogramers.atendimento.exception.AvaliacaoJaRealizadaException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.AvaliarAtendimentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliarAtendimentoUseCaseImpl implements AvaliarAtendimentoUseCase {

  private final AtendimentoGateway atendimentoGateway;

  @Override
  public void execute(RegistrarAvaliacao input) {

    var atendimento = this.atendimentoGateway.getById(input.idAtendimento());

    if (!StatusAtendimento.FINALIZADO.equals(atendimento.getStatus())) {
      throw new AtendimentoNaoFinalizadoException();
    }

    if (atendimento.getAvaliacao() != null) {
      throw new AvaliacaoJaRealizadaException();
    }

    var avaliacao = new Avaliacao(input.nota(), input.comentario());
    atendimento.registrarAvaliacao(avaliacao);

    this.atendimentoGateway.save(atendimento);
  }
}
