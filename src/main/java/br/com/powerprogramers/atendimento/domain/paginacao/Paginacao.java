package br.com.powerprogramers.atendimento.domain.paginacao;

import java.util.List;
import java.util.function.Function;

public record Paginacao<T>(int pagina, int porPagina, long total, List<T> items) {

    public <R> Paginacao<R> mapItems(final Function<T, R> mapper) {
        return new Paginacao<>(pagina, porPagina, total, items.stream().map(mapper).toList());
    }
}
