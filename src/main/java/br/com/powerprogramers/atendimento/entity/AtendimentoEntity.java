package br.com.powerprogramers.atendimento.entity;

import br.com.powerprogramers.atendimento.domain.Avaliacao;
import br.com.powerprogramers.atendimento.domain.EnfermidadeRequest;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "atendimento")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne private String idPaciente;

  @ManyToOne private String idMedico;

  @ManyToOne private String idUnidade;

  private Instant dataHoraInicio;
  private Instant dataHoraFim;

  @Enumerated(EnumType.STRING)
  private StatusAtendimento status;

  @Enumerated(EnumType.STRING)
  private List<EnfermidadeRequest> enfermidade;

  private String comentario;

  private Integer numero;

  @OneToOne private Avaliacao avaliacao;
}
