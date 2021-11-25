package com.webrixtec.buildandbeautify.exception;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.impl.SizeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import com.webrixtec.buildandbeautify.pojo.MessageResponse;



@Component
@ControllerAdvice
/** The ExceptionController class provides methods to catch exceptions. */
public abstract class ExceptionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	/** This method is used to catch the requestbody null exception **/
	@ExceptionHandler(HttpMessageConversionException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)

	public ResponseEntity<Object> handleException(HttpMessageConversionException ex, WebRequest request) {
		LOGGER.debug(ex.getLocalizedMessage(), ex);
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				"Input cannot be Empty", request.getDescription(false));
	}

	/** These method provides to catch the Resource Access Exception. */
	@ExceptionHandler(ResourceAccessException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(ResourceAccessException exception, WebRequest request) {
		LOGGER.debug(exception.getLocalizedMessage(), exception);
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "connection refused",
				request.getDescription(false));
	}

	/** These method provides to catch common Exceptions. */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(Exception exception, WebRequest request) {
		LOGGER.error(exception.getLocalizedMessage(), exception);
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), exception.getMessage(),
				request.getDescription(false));
	}

	/** These method provides to catch BindException. */
	@ExceptionHandler(BindException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(BindException e, WebRequest request) {
		LOGGER.debug(e.getLocalizedMessage(), e);
		FieldError fieldError = e.getBindingResult().getFieldError();
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				fieldError.getDefaultMessage(), request.getDescription(false));
	}

	/** These method provides to catch NullPointerException. */
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(NullPointerException e, WebRequest request) {
		LOGGER.debug(e.getLocalizedMessage(), e);
		LOGGER.debug("fail", request);
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(),
				request.getDescription(false));
	}

	/** These method provides to catch MethodArgumentNotValidException. */
	@ExceptionHandler(MethodArgumentNotValidException.class)

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(MethodArgumentNotValidException exception, WebRequest request) {
		LOGGER.debug(exception.getLocalizedMessage(), exception);
		String message = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(", "));
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message,
				request.getDescription(false));
	}

	/** These method provides to catch AuthenticationException. */
	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(AuthenticationException exception, WebRequest request) {
		LOGGER.debug(exception.getLocalizedMessage(), exception);
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				exception.getLocalizedMessage(), request.getDescription(false));
	}

	/** These method provides to catch MaxUploadSizeExceededException. */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(MaxUploadSizeExceededException e, WebRequest request) {
		LOGGER.debug(e.getLocalizedMessage(), e);
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(),
				"Maximum upload size exceeded,Please limit to 2MB", request.getDescription(false));
	}

	/** These method provides to catch SizeException. */
	@ExceptionHandler(SizeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(SizeException e, WebRequest request) {
		LOGGER.debug(e.getLocalizedMessage(), e);
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(),
				request.getDescription(false));
	}

	/** These method provides to catch FileUploadException. */
	@ExceptionHandler(FileUploadException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(FileUploadException e, WebRequest request) {
		LOGGER.debug(e.getLocalizedMessage(), e);
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(),
				request.getDescription(false));
	}

	/** These method provides to catch MultipartException. */
	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(MultipartException e, WebRequest request) {
		LOGGER.debug(e.getLocalizedMessage(), e);
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(),
				request.getDescription(false));
	}

	/** These method provides to catch NestedRuntimeException. */
	@ExceptionHandler(NestedRuntimeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleException(NestedRuntimeException e, WebRequest request) {
		LOGGER.error(e.getLocalizedMessage(), e);
		return failure(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage(),
				request.getDescription(false));
	}

	/** These method provides to catch MongoSocketException. */
//	@ExceptionHandler(MongoSocketException.class)
//
//	@ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
//	public ResponseEntity<Object> handleException(MongoSocketException exception, WebRequest request) {
//		LOGGER.debug(exception.getLocalizedMessage(), exception);
//		return failure(HttpStatus.GATEWAY_TIMEOUT.value(), HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase(),
//				sourceMessage.getMessage("code.wrong", null, LocaleContextHolder.getLocale()),
//				request.getDescription(false));
//	}

	/** This method is to catch missing request parameter exception **/
	@ExceptionHandler(MissingServletRequestParameterException.class)

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleException(MissingServletRequestParameterException exception,
			WebRequest request) {
		LOGGER.debug(exception.getLocalizedMessage(), exception);
		return failure(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
				exception.getLocalizedMessage(), request.getDescription(false));
	}

	/** These methods returns the response value. */
	protected MessageResponse response() {
		return new MessageResponse();
	}

	protected ResponseEntity<Object> response(String message) {
		MessageResponse response = new MessageResponse();
		response.setMessage(message);
		return ResponseEntity.ok(response);
	}

	protected ResponseEntity<Object> response(int status, Object result) {
		MessageResponse response = new MessageResponse();
		response.setStatus(status);
		response.setResult(result);
		return ResponseEntity.ok(response);
	}

	protected ResponseEntity<Object> response(int status, Map<String, Object> result) {
		MessageResponse response = new MessageResponse();
		response.setStatus(status);
		response.setResult(result);
		return ResponseEntity.ok(response);
	}

	protected ResponseEntity<Object> response(int status, String message, List<Object> result) {
		MessageResponse response = new MessageResponse();
		response.setStatus(status);
		response.setMessage(message);
		response.setResult(result);
		return ResponseEntity.ok(response);
	}

	protected ResponseEntity<Object> response(int status, String message) {
		MessageResponse response = new MessageResponse();
		response.setStatus(status);
		response.setMessage(message);
		return ResponseEntity.ok(response);
	}

	protected ResponseEntity<Object> response(int status, String message, Object result) {
		MessageResponse response = new MessageResponse();
		response.setStatus(status);
		response.setMessage(message);
		response.setResult(result);
		return ResponseEntity.ok(response);
	}

	protected ResponseEntity<Object> responses(int status, String message, Map<String, Object> result) {
		MessageResponse responses = new MessageResponse();
		responses.setStatus(status);
		responses.setMessage(message);
		responses.setResult(result);
		return ResponseEntity.ok(responses);
	}

	protected ResponseEntity<Object> response(Object result) {
		MessageResponse response = new MessageResponse();
		response.setResult(result);
		return ResponseEntity.ok(response);
	}

	protected ResponseEntity<Object> response(List<Object> result) {
		MessageResponse response = new MessageResponse();
		response.setResult(result);
		return ResponseEntity.ok(response);
	}

	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseEntity<Object> failure(int status, String error, String message, String path) {
		MessageResponse errorDetails = new MessageResponse(status, error, message, path);
		return ResponseEntity.accepted().body(errorDetails);
	}

	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseEntity<Object> failure(int status, String error, String message) {
		MessageResponse errorDetails = new MessageResponse(status, error, message);
		return ResponseEntity.accepted().body(errorDetails);
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseEntity<Object> failure(int status, String message) {
		MessageResponse errorDetails = new MessageResponse(status, message);
		return ResponseEntity.accepted().body(errorDetails);
	}

}
