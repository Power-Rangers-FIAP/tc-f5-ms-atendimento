package br.com.powerprogramers.atendimento.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Unidade {

    private static final int TEMPO_ATENDIMENTO = 5;

    private String id;
    private String nome;
    private String endereco;
    private List<Especialidade> especialidades;
    private int quantidadeMedicos;
    private int quantidadePacientes;
    private String tempoMedioAtendimento;

    public void atribuirQuantidadeMedicos(int quantidadeMedicos) {
        this.quantidadeMedicos = quantidadeMedicos;
    }

    public void atribuirQuantidadePacientes(int quantidadePacientes) {
        this.quantidadePacientes = quantidadePacientes;
    }

    public void atribuirTempoMedioAtendimento() {
        if (quantidadeMedicos > 0) {
            var tempoMedio = TEMPO_ATENDIMENTO;
            if (quantidadePacientes > 0) {
               tempoMedio = this.quantidadePacientes * TEMPO_ATENDIMENTO;
            }
            this.tempoMedioAtendimento = "Aproximadamente %d minutos".formatted(tempoMedio);
        }
        else {
            this.tempoMedioAtendimento = "Informação não disponível";
        }
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Especialidade {
        private String nome;
        private String descricao;
    }

}
