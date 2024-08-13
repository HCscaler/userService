package com.auth.entity;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is a wrapper class containing response to be send with appropriate
 * message, code and description
 *
 * @param <T>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestStatus<T> {

	private String code;
	private String message;
	private String uniqueErrorId;
	private T messageCode;
	private Map<String, String> errors;

	public RestStatus(final HttpStatus status, final String statusMessage) {
		this.code = Integer.toString(status.value());
		this.message = statusMessage;
	}

	public RestStatus(final HttpStatus status, final String statusMessage, final String uniqueErrorId,
			final T messageCode) {
		this.code = Integer.toString(status.value());
		this.message = statusMessage;
		this.uniqueErrorId = uniqueErrorId;
		this.messageCode = messageCode;

	}

	public RestStatus(final int code, final String message, final Map<String, String> errors) {
		this.code = Integer.toString(code);
		this.message = message;
		this.errors = errors;
	}

}
