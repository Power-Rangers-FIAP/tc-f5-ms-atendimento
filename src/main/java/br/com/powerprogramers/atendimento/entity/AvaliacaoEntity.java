package br.com.powerprogramers.atendimento.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "avaliacao")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvaliacaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne
    private AtendimentoEntity atendimento;

    private int nota;
    private String comentario;
}
