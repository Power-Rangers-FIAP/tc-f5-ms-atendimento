package br.com.powerprogramers.atendimento.usecase;

public interface ConfirmarChegadaUseCase extends UnitUseCase<String> {

  @Override
  void execute(String idAtendimento);
}
