package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.entity.ControleAtendimentoEntity;
import br.com.powerprogramers.atendimento.repository.ControleAtendimentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ControleAtendimentoGatewayImplIT {

    @InjectMocks
    private ControleAtendimentoGatewayImpl controleAtendimentoGateway;

    @Mock
    private ControleAtendimentoRepository controleAtendimentoRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    @BeforeEach
    void setUp() {
        reset(controleAtendimentoRepository);
    }

    @Test
    void deveBuscarNumeroQuandoNaoExistirControleAtendimento() {
        String idUnidade = "unidade1";

        when(mongoTemplate.findOne(any(Query.class), eq(ControleAtendimentoEntity.class))).thenReturn(null);

        Integer numeroGerado = controleAtendimentoGateway.buscarNumero(idUnidade);

        assertEquals(1, numeroGerado);
        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(ControleAtendimentoEntity.class));
        verify(controleAtendimentoRepository, times(1)).save(any(ControleAtendimentoEntity.class));
    }

    @Test
    void deveBuscarNumeroQuandoControleAtendimentoExistir() {
        String idUnidade = "unidade1";

        var entidadeExistente = new ControleAtendimentoEntity();
        entidadeExistente.setIdUnidade(idUnidade);
        entidadeExistente.setUltimoNumeroGerado(5);

        when(mongoTemplate.findOne(any(Query.class), eq(ControleAtendimentoEntity.class))).thenReturn(entidadeExistente);

        Integer numeroGerado = controleAtendimentoGateway.buscarNumero(idUnidade);

        assertEquals(6, numeroGerado);
        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(ControleAtendimentoEntity.class));
        verify(controleAtendimentoRepository, times(1)).save(any(ControleAtendimentoEntity.class));
    }
}
