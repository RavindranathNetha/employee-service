package com.learning.ark.training.exception;

import static com.learning.ark.training.util.Constants.DIAGNOSTIC_ID;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learning.ark.training.dto.ExceptionResponse;

@ControllerAdvice
public class TrainingExceptionHandler {

	@ExceptionHandler(TrainingException.class)
	public ResponseEntity<ExceptionResponse> handle(TrainingException e) {
		if (HttpStatus.NO_CONTENT.equals(e.getStatus())) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.status(e.getStatus())
				.body(new ExceptionResponse(
						(String) Optional.ofNullable(MDC.get(DIAGNOSTIC_ID)).orElse(UUID.randomUUID().toString()),
						e.getMessage()));
	}

	public ResponseEntity<ExceptionResponse> hadle(AbstractErrorWebExceptionHandler ew) {
		return ResponseEntity.noContent().build();
	}

}
