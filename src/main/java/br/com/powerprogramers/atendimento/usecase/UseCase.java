package br.com.powerprogramers.atendimento.usecase;

public interface UseCase <I, O> {

    O execute(I input);

}
