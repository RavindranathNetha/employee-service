package com.learning.ark.training.dto;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = -1073491696243082309L;

	private String id;

	private String message;

	public ExceptionResponse() {
	}

	public ExceptionResponse(String id, String message) {
		this.id = id;
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("id:").append(id).append(",");
		sb.append("message:=").append(message);
		sb.append("}");
		return sb.toString();
	}

}