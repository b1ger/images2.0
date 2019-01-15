package org.ontario.images.exceptions;

public class CannotActivateYouSelfException extends RuntimeException {
    public CannotActivateYouSelfException() {
        super("You cannot activate or deactivate yourself");
    }
}
