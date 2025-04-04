package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record ConsultarAvaliacao(int pagina, int porPagina, String idUnidade, String idMedico) {

    public boolean chavePesquisaValida() {
        return idUnidade != null ^ idMedico != null;
    }

}
