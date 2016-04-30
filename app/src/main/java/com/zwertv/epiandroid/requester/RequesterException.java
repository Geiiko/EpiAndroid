package com.zwertv.epiandroid.requester;

/**
 * Created by Elliott on 28/01/2016.
 */
public class RequesterException extends RuntimeException {
    /**
     * Constructs a new {@code RequesterException} with the current stack trace
     * and the specified detail message.
     *
     * @param detailMessage
     *            the detail message for this exception.
     */
    public RequesterException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs a new {@code RequesterException} with the current stack trace,
     * the specified detail message and the specified cause.
     *
     * @param detailMessage
     *            the detail message for this exception.
     * @param throwable
     *            the cause of this exception.
     */
    public RequesterException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    /**
     * Constructs a new {@code RequesterException} with the current stack trace
     * and the specified cause.
     *
     * @param throwable
     *            the cause of this exception.
     */
    public RequesterException(Throwable throwable) {
        super(throwable);
    }
}
