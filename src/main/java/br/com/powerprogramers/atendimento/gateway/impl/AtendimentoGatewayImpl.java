package br.com.powerprogramers.atendimento.gateway.impl;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.entity.AvaliacaoEntity;
import br.com.powerprogramers.atendimento.gateway.AtendimentoGateway;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import br.com.powerprogramers.atendimento.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AtendimentoGatewayImpl implements AtendimentoGateway {

    private static final AtendimentoMapper atendimentoMapper = AtendimentoMapper.INSTANCE;
    private final AtendimentoRepository atendimentoRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    @Override
    public AtendimentoEntity getById(String idAtendimento) {
        return this.atendimentoRepository.findById(idAtendimento)
                .orElseThrow(RuntimeException::new); //FIXME mudar depois exception personalizada
    }

    @Override
    public void save(AvaliacaoEntity avaliacao) {
        this.avaliacaoRepository.save(avaliacao);
    }

    @Override
    public void confirmarChegada(String idAtendimento) {
        var atendimento = this.getById(idAtendimento);
        //TODO implementar mudança de status do atendimento
        this.atendimentoRepository.save(atendimento);
    }

    @Override
    public void finalizarAtendimento(FinalizarAtendimento finalizarAtendimento) {
        var atendimento = this.getById(finalizarAtendimento.idAtendimento());
        //TODO implementar mudança de status do atendimento
        atendimento.atribuirMedico(finalizarAtendimento.idMedico());
        atendimento.atribuirComentario(finalizarAtendimento.comentario());
        this.atendimentoRepository.save(atendimento);
    }

    @Override
    public Atendimento registrarEnfermidade(RegistrarAtendimento registrarAtendimento) {
        var atendimento = Atendimento.iniciarAtendimento(registrarAtendimento);
        var atendimentoEntity = this.atendimentoRepository.save(AtendimentoEntity.from(atendimento));
        return atendimentoMapper.toDomain(atendimentoEntity);
    }
}
