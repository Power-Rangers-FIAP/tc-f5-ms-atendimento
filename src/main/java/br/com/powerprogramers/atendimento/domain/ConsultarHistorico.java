package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record ConsultarHistorico(int pagina, int porPagina, String idPaciente, String idMedico) {

    public boolean chavePesquisaValida() {
        return idPaciente != null ^ idMedico != null;
    }

}
