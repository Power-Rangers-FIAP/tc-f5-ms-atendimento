package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.Avaliacao;

public interface AvaliarAtendimentoUseCase extends UnitUseCase<Avaliacao> {

    @Override
    void execute(Avaliacao input);

}
