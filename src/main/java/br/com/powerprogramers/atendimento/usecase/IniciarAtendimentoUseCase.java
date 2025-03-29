package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Pagina;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;

public interface IniciarAtendimentoUseCase extends UseCase<Pagina, Paginacao<Unidade>> {

    @Override
    Paginacao<Unidade> execute(Pagina input);

}
