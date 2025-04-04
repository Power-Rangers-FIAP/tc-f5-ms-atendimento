package br.com.powerprogramers.atendimento.service.impl;

import br.com.powerprogramers.atendimento.domain.Unidade;
import br.com.powerprogramers.atendimento.domain.paginacao.Paginacao;
import br.com.powerprogramers.atendimento.service.UnidadeService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UnidadeServiceImpl implements UnidadeService {

  @Value("${unidade.api.url}")
  private String unidadeApiUrl;

  private final RestTemplate restTemplate;

  @Override
  public Paginacao<Unidade> listarUnidade(Pageable pageable) {
    String url = String.format("%s", unidadeApiUrl);
    var unidades = restTemplate.getForObject(url, Unidade[].class);

    if (unidades != null) {
      int elementoInicial = pageable.getPageNumber() * pageable.getPageSize();

      List<Unidade> listSlicedUnidade =
          Arrays.asList(unidades)
              .subList(elementoInicial, elementoInicial + pageable.getPageSize());

      return new Paginacao<>(
          pageable.getPageNumber(), pageable.getPageSize(), unidades.length, listSlicedUnidade);
    }
    return new Paginacao<>(pageable.getPageNumber(), pageable.getPageSize(), 0, new ArrayList<>());
  }
}
