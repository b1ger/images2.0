package org.ontario.images.utils;

import org.ontario.images.web.api.request.NewUserRequest;
import org.springframework.util.StringUtils;

public class SecurityUtils {

    public SecurityUtils() {
    }

    public static boolean checkPasswordLength(final String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= NewUserRequest.MIN_PASSWORD_LENGTH &&
                password.length() <= NewUserRequest.MAX_PASSWORD_LENGTH;
    }
}
