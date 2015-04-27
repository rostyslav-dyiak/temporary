package com.kb.exception;

/**
 * This is custom exception, which describes situation of failed read operation.
 * 
 * @author oyats
 * 
 */
public class ContentReadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for ContentSaveException.
	 */
	public ContentReadException() {
		super();
	}

	/**
	 * Constructor for ContentReadException using parameters message, cause,
	 * enableSuppression, writableStackTrace.
	 * 
	 * @param message
	 *            Message of exception.
	 * @param cause
	 *            Cause of exception.
	 * @param enableSuppression
	 *            Flag to enable Suppression.
	 * @param writableStackTrace
	 *            Flag to set Stack Trace writable.
	 */
	public ContentReadException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Constructor for ContentReadException using parameters message, cause.
	 * 
	 * @param message
	 *            Message of exception.
	 * @param cause
	 *            Cause of exception.
	 */
	public ContentReadException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for ContentReadException using parameters message.
	 * 
	 * @param message
	 *            Message of exception.
	 */
	public ContentReadException(final String message) {
		super(message);
	}

	/**
	 * Constructor for ContentReadException using parameters cause.
	 * 
	 * @param cause
	 *            Cause of exception.
	 */
	public ContentReadException(final Throwable cause) {
		super(cause);
	}
}
