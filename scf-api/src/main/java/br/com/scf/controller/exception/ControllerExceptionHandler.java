package br.com.scf.controller.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import br.com.scf.controller.response.ErrorMessageResponse;
import br.com.scf.exception.FinancasNegocioException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(FinancasNegocioException.class)
	public ResponseEntity<ErrorMessageResponse> resourceNotFoundException(FinancasNegocioException ex, WebRequest request) {
		var errorMessag = ErrorMessageResponse.builder().statusCode(HttpStatus.NOT_FOUND.value())
				.description(ex.getMessage()).timestamp(LocalDateTime.now()).message("Erro de negócio!")
				.url(request.getDescription(false)).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessag);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorMessageResponse>> handleConstraintViolationException(MethodArgumentNotValidException ex,
			WebRequest request) {
		List<ErrorMessageResponse> errors = ex.getFieldErrors().stream().map(erros -> {
			return ErrorMessageResponse.builder().statusCode(HttpStatus.BAD_REQUEST.value())
					.description(erros.toString()).timestamp(LocalDateTime.now())
					.message(erros.getDefaultMessage()).url(request.getDescription(false)).build();
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessageResponse> globalExceptionHandler(Exception ex, WebRequest request) {
		var errorMessag = ErrorMessageResponse.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.description(ex.getLocalizedMessage()).timestamp(LocalDateTime.now()).message("Error inesperado!")
				.url(request.getDescription(false)).build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessag);
	}
}