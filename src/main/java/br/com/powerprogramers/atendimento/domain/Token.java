package br.com.powerprogramers.atendimento.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record Token(@JsonProperty("access_token") String token) {
}
