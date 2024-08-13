package com.auth.entity;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * Wrapper for rest response containing status and message
 * 
 *
 * @param <T>
 */

@Getter
@Setter
public class RestResponse<T> {

	private T data;
	private RestStatus<?> status;
	private RestCustom custom;

	public RestResponse() {
	}

	public RestResponse(final T data, final RestStatus<?> status) {
		this.data = data;
		this.status = status;
	}

	public RestResponse(final T data) {
		this.data = data;
	}

	public RestResponse(HttpStatus status, String message, T data) {
		this.data = (T) data;
		this.status = new RestStatus<>(status, message);
	}

	public RestResponse(final T data, final RestStatus<?> status, final RestCustom custom) {
		this.data = data;
		this.status = status;
		this.custom = custom;
	}

	public RestResponse(HttpStatus status, String message, Map<String, String> errors) {
		this.status = new RestStatus<Map<String, String>>(status.value(), message, errors);
	}

	public void setStatus(RestStatus<?> status) {
		this.status = status;
	}
}