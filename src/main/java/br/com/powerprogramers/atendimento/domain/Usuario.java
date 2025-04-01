package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record Usuario(String id, String email, String senha) {
}
