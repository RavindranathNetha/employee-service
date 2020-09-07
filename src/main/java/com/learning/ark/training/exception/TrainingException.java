package com.learning.ark.training.exception;

import org.springframework.http.HttpStatus;

public class TrainingException extends RuntimeException {

	private static final long serialVersionUID = 5499132579060111669L;

	private String message;

	private HttpStatus status;

	/**
	 * @param status
	 * @param message
	 */
	public TrainingException(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
