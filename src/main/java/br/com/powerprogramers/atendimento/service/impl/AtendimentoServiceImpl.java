package br.com.powerprogramers.atendimento.service.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AtendimentoServiceImpl {

    @Value("${atendimento.tempo-limite-espera}")
    private Long tempoLimiteEspera;
    private static final AtendimentoMapper atendimentoMapper = AtendimentoMapper.INSTANCE;
    private final AtendimentoRepository atendimentoRepository;

    @Scheduled(fixedRate = 60000)
    public void verificarAtendimentosAtrasados() {
        log.info("Verificando atendimentos atrasados :: Início");
        List<AtendimentoEntity> atendimentos = this.atendimentoRepository.findByStatus(StatusAtendimento.ABERTO);

        for (Atendimento atendimento : atendimentos.stream().map(atendimentoMapper::toDomain).toList()) {
            if (atendimento.estaAtrasadoAhMaisDe(tempoLimiteEspera)) {
                log.info("Atendimento atrasado encontrado: {}", atendimento.getId());
                atendimento.finalizarAtendimento("Sistema", "Paciente atrasado a mais de %d minutos".formatted(tempoLimiteEspera));
                this.atendimentoRepository.save(atendimentoMapper.toEntity(atendimento));
            }
        }
        log.info("Verificação de atendimentos atrasados :: Fim");
    }

}
