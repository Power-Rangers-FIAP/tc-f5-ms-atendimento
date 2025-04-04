//package br.com.powerprogramers.atendimento.gateway.impl;
//
//import br.com.powerprogramers.atendimento.domain.ControleAtendimento;
//import br.com.powerprogramers.atendimento.entity.ControleAtendimentoEntity;
//import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
//import br.com.powerprogramers.atendimento.repository.ControleAtendimentoRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class ControleAtendimentoGatewayImplTest {
//
//    @InjectMocks
//    private ControleAtendimentoGatewayImpl controleAtendimentoGateway;
//
//    @Mock
//    private ControleAtendimentoRepository controleAtendimentoRepository;
//
//    private AtendimentoMapper atendimentoMapper = AtendimentoMapper.INSTANCE;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void deveBuscarNumeroQuandoNaoExistirControleAtendimento() {
//        // Arrange
//        String idUnidade = "unidade1";
//
//        when(controleAtendimentoRepository.findById(idUnidade)).thenReturn(Optional.empty());
//        when(controleAtendimentoRepository.save(any())).thenReturn(new ControleAtendimentoEntity());
//
//        // Act
//        Integer numeroGerado = controleAtendimentoGateway.buscarNumero(idUnidade);
//
//        // Assert
//        assertEquals(1, numeroGerado); // Criado um novo registro e iniciado em 1
//        verify(controleAtendimentoRepository, times(1)).findById(idUnidade);
//        verify(controleAtendimentoRepository, times(1)).save(any(ControleAtendimentoEntity.class));
//    }
//
//    @Test
//    void deveBuscarNumeroQuandoControleAtendimentoExiste() {
//        // Arrange
//        String idUnidade = "unidade1";
//        ControleAtendimentoEntity entity = new ControleAtendimentoEntity();
//        entity.setId(idUnidade);
//        entity.setNumeroGerado(5);
//
//        when(controleAtendimentoRepository.findById(idUnidade)).thenReturn(Optional.of(entity));
//        when(controleAtendimentoRepository.save(any())).thenReturn(entity);
//
//        // Act
//        Integer numeroGerado = controleAtendimentoGateway.buscarNumero(idUnidade);
//
//        // Assert
//        assertEquals(6, numeroGerado); // Incrementa o número gerado
//        verify(controleAtendimentoRepository, times(1)).findById(idUnidade);
//        verify(controleAtendimentoRepository, times(1)).save(any(ControleAtendimentoEntity.class));
//    }
//
//    @Test
//    void deveAtualizarQuantidadePacientesComSucesso() {
//        // Arrange
//        String idUnidade = "unidade1";
//        int quantidadePacientes = 100;
//
//        ControleAtendimentoEntity entity = new ControleAtendimentoEntity();
//        entity.setId(idUnidade);
//
//        when(controleAtendimentoRepository.findById(idUnidade)).thenReturn(Optional.of(entity));
//
//        // Act
//        controleAtendimentoGateway.atualizarQuantidadePacientes(idUnidade, quantidadePacientes);
//
//        // Assert
//        verify(controleAtendimentoRepository, times(1)).save(any(ControleAtendimentoEntity.class));
//        assertEquals(100, entity.getQuantidadePacientes());
//    }
//
//    @Test
//    void deveCriarControleSeNaoExistirAoAtualizarQuantidadePacientes() {
//        // Arrange
//        String idUnidade = "unidade1";
//        int quantidadePacientes = 50;
//
//        when(controleAtendimentoRepository.findById(idUnidade)).thenReturn(Optional.empty());
//
//        // Act
//        controleAtendimentoGateway.atualizarQuantidadePacientes(idUnidade, quantidadePacientes);
//
//        // Assert
//        verify(controleAtendimentoRepository, times(1)).save(any(ControleAtendimentoEntity.class));
//    }
//}
