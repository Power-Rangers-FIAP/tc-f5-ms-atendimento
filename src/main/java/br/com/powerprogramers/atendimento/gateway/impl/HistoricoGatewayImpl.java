package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.ConsultarHistorico;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.gateway.HistoricoGateway;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HistoricoGatewayImpl implements HistoricoGateway {

    private final AtendimentoRepository atendimentoRepository;

    @Override
    public Page<AtendimentoEntity> consultarHistorico(ConsultarHistorico input) {
        var pageable = PageRequest.of(input.pagina(), input.porPagina());

        Page<AtendimentoEntity> atedimentos = null;
        if (input.idPaciente() != null) {
            atedimentos = this.atendimentoRepository.findByPacienteId(pageable, input.idPaciente());
        }
        else {
            atedimentos = this.atendimentoRepository.findByMedicoId(pageable, input.idMedico());
        }

        return atedimentos;
    }
}
