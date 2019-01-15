package org.ontario.images.exceptions.security;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException() {
        super("Email already in use");
    }
}
