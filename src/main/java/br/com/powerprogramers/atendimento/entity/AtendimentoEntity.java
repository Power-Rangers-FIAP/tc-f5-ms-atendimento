package br.com.powerprogramers.atendimento.entity;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.EnfermidadeRequest;
import br.com.powerprogramers.atendimento.domain.Historico;
import br.com.powerprogramers.atendimento.domain.enums.Enfermidade;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.mapper.AtendimentoMapper;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collation = "atendimento")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private List<EnfermidadeRequest> enfermidade;

    private String comentario;

    private Integer numero;

    public void atribuirMedico(String medicoId) {
        this.medicoId = medicoId;
    }

    public void atribuirComentario(String comentario) {
        this.medicoId = comentario;
    }

    public static AtendimentoEntity from(final Atendimento atendimento) {
        return AtendimentoEntity.builder()
                .id(atendimento.id())
                .pacienteId(atendimento.idPaciente())
                .medicoId(atendimento.idMedico())
                .unidadeId(atendimento.idUnidade())
                .dataHoraInicio(atendimento.dataHoraInicio())
                .dataHoraFim(atendimento.dataHoraFim())
                .status(atendimento.status())
                .enfermidade(atendimento.enfermidade())
                .comentario(atendimento.comentario())
                .numero(atendimento.numero())
                .build();
    }
}
