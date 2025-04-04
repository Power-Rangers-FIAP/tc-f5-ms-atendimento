package br.com.powerprogramers.atendimento.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record DominioException(String message, List<ErroValidacao> validacoes) {

    public DominioException(String message) {
        this(message, null);
    }

}
