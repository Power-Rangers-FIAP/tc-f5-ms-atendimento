package br.com.powerprogramers.atendimento.usecase;

import br.com.powerprogramers.atendimento.domain.RegistrarEnfermidade;

public interface RegistrarEnfermidadeUseCase extends UseCase<RegistrarEnfermidade, RegistrarEnfermidade> {

    @Override
    RegistrarEnfermidade execute(RegistrarEnfermidade input);

}
