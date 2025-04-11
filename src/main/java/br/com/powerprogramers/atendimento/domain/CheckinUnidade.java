package br.com.powerprogramers.atendimento.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CheckinUnidade {

    private long id;
    private List<MedicosAtendimento> medicosAtendendo;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class MedicosAtendimento {
        private int idMedico;
        private LocalDateTime checkIn;
        private LocalDateTime data;
    }
}
