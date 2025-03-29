package br.com.powerprogramers.atendimento.gateway;

import br.com.powerprogramers.atendimento.domain.FinalizarAtendimento;
import br.com.powerprogramers.atendimento.domain.RegistrarEnfermidade;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.entity.AvaliacaoEntity;

public interface AtendimentoGateway {

    AtendimentoEntity getById(String idAtendimento);

    void save(AvaliacaoEntity avaliacao);

    void confirmarChegada(String idAtendimento);

    void finalizarAtendimento(FinalizarAtendimento finalizarAtendimento);

    RegistrarEnfermidade registrarEnfermidade(RegistrarEnfermidade registrarEnfermidade);

}
