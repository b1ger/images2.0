package org.ontario.images.exceptions.security;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super("Email address not registered");
    }
}
