package br.com.powerprogramers.atendimento.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ControleAtendimento {
  private String idUnidade;
  private Integer quantidadePacientes;
  private Integer ultimoNumeroGerado;

  public static ControleAtendimento criarNovo(String idUnidade) {
    return new ControleAtendimento(idUnidade, 0, 0);
  }

  public Integer incrementarNumeroGerado() {
    return ++this.ultimoNumeroGerado;
  }

  public void atualizarQuantidadePacientes(Integer quantidadePacientes) {
    this.quantidadePacientes += quantidadePacientes;
  }
}
