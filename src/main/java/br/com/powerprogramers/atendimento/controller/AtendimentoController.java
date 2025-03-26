package br.com.powerprogramers.atendimento.controller;

import br.com.powerprogramers.atendimento.api.HolaApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AtendimentoController implements HolaApi {

    @Override
    public ResponseEntity<String> hola() {
        return ResponseEntity.ok("Hola!");
    }
}
