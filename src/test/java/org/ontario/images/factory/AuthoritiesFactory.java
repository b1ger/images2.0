package org.ontario.images.factory;

import org.ontario.images.config.constants.AuthoritiesConstants;
import org.ontario.images.model.Authority;

public final class AuthoritiesFactory {

    private AuthoritiesFactory() {}

    public static final Authority ADMIN_AUTHORITY = new Authority(AuthoritiesConstants.ROLE_ADMIN, AuthoritiesConstants.DESCRIPTION_ADMIN);
    public static final Authority USER_AUTHORITY = new Authority(AuthoritiesConstants.ROLE_USER, AuthoritiesConstants.DESCRIPTION_USER);
}
