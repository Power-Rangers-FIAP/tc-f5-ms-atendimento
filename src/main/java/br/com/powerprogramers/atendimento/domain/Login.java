package br.com.powerprogramers.atendimento.domain;

import lombok.Builder;

@Builder
public record Login(String email, String senha) {}
