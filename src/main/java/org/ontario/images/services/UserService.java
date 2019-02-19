package org.ontario.images.services;

import org.ontario.images.model.User;
import org.ontario.images.model.projections.ReadableUser;
import org.ontario.images.model.projections.UpdatableUser;
import org.ontario.images.web.api.request.NewUserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(NewUserRequest request, boolean isActive);

    User updateUser(User user);

    void deleteUser(Long userId);

    Optional<UpdatableUser> loadUpdatableUserById(Long userId);

    Optional<User> loadUserById(Long userId);

    User loadUserByEmail(String email);

    List<ReadableUser> loadUsersByAuthority(String role);

    void changePassword(User user, String newPassword);

    Optional<ReadableUser> loadReadableByEmail(String email);
}
