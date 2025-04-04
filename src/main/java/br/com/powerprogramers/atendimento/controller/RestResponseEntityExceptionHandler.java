package br.com.powerprogramers.atendimento.controller;

import br.com.powerprogramers.atendimento.exception.AtendimentoException;
import br.com.powerprogramers.atendimento.exception.DominioException;
import br.com.powerprogramers.atendimento.exception.ErroValidacao;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AtendimentoException.class)
  public ResponseEntity<DominioException> atendimentoBaseException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(new DominioException(ex.getMessage()));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<DominioException> illegalArgumentException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new DominioException(ex.getMessage()));
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      @Nullable MethodArgumentNotValidException ex,
      @Nullable HttpHeaders headers,
      @Nullable HttpStatusCode status,
      @Nullable WebRequest request) {

    if (ex == null || status == null || request == null) {
      return generateServerErrorResponse();
    }

    List<ErroValidacao> errors = new ArrayList<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.add(new ErroValidacao(fieldName, errorMessage));
            });
    var resposta = new DominioException("Erro de validação", errors);
    return ResponseEntity.status(status).body(resposta);
  }

  private ResponseEntity<Object> generateServerErrorResponse() {
    var resposta = new DominioException("Internal server error");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
  }
}
