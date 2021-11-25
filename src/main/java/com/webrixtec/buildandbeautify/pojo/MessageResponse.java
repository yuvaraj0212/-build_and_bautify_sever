package com.webrixtec.buildandbeautify.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int status;
	private String error;
	private String message;
	private String path;
	private Object result;

	public MessageResponse() {
		super();
	}

	public MessageResponse(int status, String error, String message, String path) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public MessageResponse(int status, String error, String message) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
	}

	public MessageResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
