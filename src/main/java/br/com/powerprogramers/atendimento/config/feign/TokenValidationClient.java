package br.com.powerprogramers.atendimento.config.feign;

import br.com.powerprogramers.atendimento.config.security.FeignConfig;
import br.com.powerprogramers.atendimento.domain.dto.TokenValidationRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "tc-f5-ms-login",
        path = "/api/token-validation",
        configuration = FeignConfig.class)
public interface TokenValidationClient {

    @PostMapping("/validate")
    Boolean validate(@RequestBody TokenValidationRequestDto request);
}