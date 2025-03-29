package br.com.powerprogramers.atendimento.entity;

import br.com.powerprogramers.atendimento.domain.Avaliacao;
import br.com.powerprogramers.atendimento.domain.Historico;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
public class AtendimentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private String pacienteId;

    @ManyToOne
    private String medicoId;

    @ManyToOne
    private String unidadeId;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    @Enumerated(EnumType.STRING)
    private StatusAtendimento status;

    private String comentario;

    public void atribuirMedico(String medicoId) {
        this.medicoId = medicoId;
    }

    public void atribuirComentario(String comentario) {
        this.medicoId = comentario;
    }

    public static Historico toHistorico(final AtendimentoEntity entity) {
        return AtendimentoMapper.INSTANCE.toDomain(entity);
    }
}
