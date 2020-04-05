package org.example;

public class ServiceException extends RuntimeException {

    /**
     * Constructs a new runtime exception with {@code null} as its detail message.
     */
    public ServiceException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message and cause.
     */
    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

}
