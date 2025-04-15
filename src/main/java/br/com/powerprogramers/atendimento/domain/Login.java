package br.com.powerprogramers.atendimento.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Login(@JsonProperty("username") String usuario, @JsonProperty("password") String senha) {}
