package br.com.powerprogramers.atendimento.controller;

import br.com.powerprogramers.atendimento.api.AtendimentoApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AtendimentoController implements AtendimentoApi {

}
