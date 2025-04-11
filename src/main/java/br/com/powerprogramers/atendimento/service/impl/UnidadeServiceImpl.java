package br.com.powerprogramers.atendimento.service.impl;

import br.com.powerprogramers.atendimento.domain.CheckinUnidade;
import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.gateway.ControleAtendimentoGateway;
import br.com.powerprogramers.atendimento.service.UnidadeService;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnidadeServiceImpl implements UnidadeService {

  @Value("${unidade.api.url}")
  private String unidadeApiUrl;

  private final RestTemplate restTemplate;
  private final ControleAtendimentoGateway controleAtendimentoGateway;

  @Override
  public Paginacao<Unidade> listarUnidade(Pageable pageable) {
    String uri = unidadeApiUrl + "api/unidades";
    String url = String.format("%s", uri);
    var unidades = restTemplate.getForObject(url, Unidade[].class);

    if (unidades != null) {
      Map<String, Object> params = new HashMap<>();
      params.put("data", ZonedDateTime.now().toLocalDate());
      for (Unidade unidade : unidades) {
        int quantidadeMedicos = 0;
        try {
          uri = unidadeApiUrl + "api/unidades/medicos/%s/checkin?data={data}".formatted(unidade.getId());
          url = String.format("%s", uri);

          CheckinUnidade checkinUnidade = restTemplate.getForObject(url, CheckinUnidade.class, params);

          if (checkinUnidade != null) {
            quantidadeMedicos = checkinUnidade.getMedicosAtendendo().size();
          }
        } catch (Exception ex) {
          log.error("Sem medicos atendendo na unidade: {}", unidade.getId());
        }

        unidade.atribuirQuantidadeMedicos(quantidadeMedicos);

        var quantidadePacientes = this.controleAtendimentoGateway.buscarQuantidadePacientes(unidade.getId());

        unidade.atribuirQuantidadePacientes(quantidadePacientes);
        unidade.atribuirTempoMedioAtendimento();
      }
      return geraPaginacao(pageable, unidades);
    }
    return geraPaginacaoNula(pageable);
  }


  private Paginacao<Unidade> geraPaginacao(Pageable pageable, Unidade[] unidades) {
    int elementoInicial = pageable.getPageNumber() * pageable.getPageSize();

    int elementoFinal = elementoInicial + Math.min(unidades.length, pageable.getPageSize());

    if (elementoInicial >= unidades.length) {
      return geraPaginacaoNula(pageable);
    }

    List<Unidade> listSlicedUnidade =
            Arrays.asList(unidades)
                    .subList(elementoInicial, elementoFinal);

    return new Paginacao<>(
            pageable.getPageNumber(), pageable.getPageSize(), unidades.length, listSlicedUnidade);
  }

  private Paginacao<Unidade> geraPaginacaoNula(Pageable pageable) {
    return new Paginacao<>(pageable.getPageNumber(), pageable.getPageSize(), 0, new ArrayList<>());
  }
}
