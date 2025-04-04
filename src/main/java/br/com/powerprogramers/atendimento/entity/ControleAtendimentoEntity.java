package br.com.powerprogramers.atendimento.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "controle-atendimento")
@AllArgsConstructor
@Getter
public class ControleAtendimentoEntity {
  @Id private String idUnidade;
  private Integer quantidadePacientes;
  private Integer ultimoNumeroGerado;
}
