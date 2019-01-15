package org.ontario.images.exceptions;

public class CannotDeleteYouSelfException extends RuntimeException {
    public CannotDeleteYouSelfException() {
        super("You cannot delete yourself");
    }
}
