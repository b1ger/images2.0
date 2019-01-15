package org.ontario.images.exceptions.security;

public class InvalidActivationKeyException extends RuntimeException {
    public InvalidActivationKeyException() {
        super("Invalid activation key");
    }
}
