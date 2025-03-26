package br.com.powerprogramers.atendimento.entity;

import br.com.powerprogramers.atendimento.domain.enums.Enfermidade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class RegistroEnfermidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Enfermidade enfermidade;

    private String observacao;

    @ManyToOne
    private String pacienteId;

    @ManyToOne
    private String unidadeId;

    private LocalDateTime dataRegistro;
}
