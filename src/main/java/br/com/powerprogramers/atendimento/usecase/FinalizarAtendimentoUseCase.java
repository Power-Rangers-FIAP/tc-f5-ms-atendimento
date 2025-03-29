package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;

public interface FinalizarAtendimentoUseCase extends UnitUseCase<FinalizarAtendimento> {

    @Override
    void execute(FinalizarAtendimento input);
}
