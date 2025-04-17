package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record FinalizarAtendimento(String idAtendimento, String idMedico, String comentario, String userType) {

    public boolean ehMedico() {
        return userType.equalsIgnoreCase("DOCTOR");
    }

}
