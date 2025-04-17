package br.com.powerprogramers.atendimento.usecase.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;
import br.com.powerprogramers.atendimento.exception.JaPossuiRegistroAtendimentoEmAbertoException;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import br.com.powerprogramers.atendimento.usecase.RegistrarEnfermidadeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrarEnfermidadeUseCaseImpl implements RegistrarEnfermidadeUseCase {

  private final AtendimentoGateway atendimentoGateway;
  private final ControleAtendimentoGateway controleAtendimentoGateway;

  @Override
  public Atendimento execute(RegistrarAtendimento input) {

    var listaEnfermidades = input.enfermidades();
    if (listaEnfermidades.isEmpty()) {
      throw new IllegalArgumentException("Nenhuma enfermidade informada");
    }

    var atendimentoEmAberto = this.atendimentoGateway.existeAtendimentoAberto(input.idPaciente());
    if (atendimentoEmAberto != null) {
      throw new JaPossuiRegistroAtendimentoEmAbertoException();
    }

    var atendimento = Atendimento.iniciarAtendimento(input);
    var proximoNumero = this.controleAtendimentoGateway.buscarNumero(atendimento.getIdUnidade());
    atendimento.definirNumero(proximoNumero);

    return this.atendimentoGateway.save(atendimento);
  }
}
