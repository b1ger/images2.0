package org.ontario.images.factory;

import org.ontario.images.web.api.request.NewUserRequest;

public final class UsersFactory {

    private UsersFactory() {
    }

    public static final Long USER_ID = 1L;

    public static final String USER_PASSWORD = "userPassword";
    public static final String UPDATED_USER_PASSWORD = "user2updated";

    public static final String USER_EMAIL = "system@localhost.com";
    public static final String UPDATED_USER_EMAIL = "updateUser@localhost.com";

    public static final String USER_FIRSTNAME = "System";
    public static final String UPDATED_USER_FIRSTNAME = "updateUserFirstName";

    public static final String USER_LASTNAME = "System";
    public static final String UPDATED_USER_LASTNAME = "updateUserLastName";

    public static final String USER_ACTIVATION_KEY = "activation-key";

    public static NewUserRequest createNewUserReq() {
        return new NewUserRequest(
                UsersFactory.USER_FIRSTNAME,
                UsersFactory.USER_LASTNAME,
                UsersFactory.USER_EMAIL,
                UsersFactory.USER_PASSWORD
        );
    }
}
