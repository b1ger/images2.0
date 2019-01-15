package org.ontario.images.exceptions;

public class ValidationNewUserReqException extends RuntimeException {
    public ValidationNewUserReqException(final String errors) {
        super(errors);
    }
}
