package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record Unidade(String id, String nome) {
}
