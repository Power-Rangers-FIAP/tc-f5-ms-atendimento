package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.entity.ControleAtendimentoEntity;
import br.com.powerprogramers.atendimento.repository.ControleAtendimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ControleAtendimentoGatewayImplTest {

    @InjectMocks
    private ControleAtendimentoGatewayImpl controleAtendimentoGateway;

    @Mock
    private ControleAtendimentoRepository controleAtendimentoRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarNumeroQuandoNaoExistirControleAtendimento() {
        String idUnidade = "unidade1";

        when(mongoTemplate.findOne(any(Query.class), eq(ControleAtendimentoEntity.class))).thenReturn(null);

        Integer numeroGerado = controleAtendimentoGateway.buscarNumero(idUnidade);

        assertEquals(1, numeroGerado);
        verify(controleAtendimentoRepository, times(1)).save(any(ControleAtendimentoEntity.class));
    }

    @Test
    void deveBuscarNumeroQuandoControleAtendimentoExistir() {
        String idUnidade = "unidade1";
        var entidadeExistente = new ControleAtendimentoEntity(idUnidade, 0, 5);

        when(mongoTemplate.findOne(any(Query.class), eq(ControleAtendimentoEntity.class))).thenReturn(entidadeExistente);

        Integer numeroGerado = controleAtendimentoGateway.buscarNumero(idUnidade);

        assertEquals(6, numeroGerado);
        verify(controleAtendimentoRepository, times(1)).save(any(ControleAtendimentoEntity.class));
    }
}