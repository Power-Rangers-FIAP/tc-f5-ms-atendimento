package br.com.powerprogramers.atendimento.entity;

import br.com.powerprogramers.atendimento.domain.enums.Enfermidade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Builder
public class RegistroEnfermidadeEntity { //FIXME: atribuir um nome melhor
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private List<Enfermidade> enfermidade;

    private String observacao;

    @ManyToOne
    private String pacienteId;

    @ManyToOne
    private String unidadeId;

    private LocalDateTime dataRegistro;
}
