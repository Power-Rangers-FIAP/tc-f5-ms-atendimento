package br.com.powerprogramers.atendimento.gateway;

public interface ControleAtendimentoGateway {

  Integer buscarNumero(String idUnidade);

  void atualizarQuantidadePacientes(String idUnidade, Integer quantidadePacientes);
}
