package br.com.powerprogramers.atendimento.entity;

import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
