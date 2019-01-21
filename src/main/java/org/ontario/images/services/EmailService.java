package org.ontario.images.services;

import org.ontario.images.model.User;

public interface EmailService {

    void sendActivationEmail(User user);
}
