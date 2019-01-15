package org.ontario.images.services.impl;

import org.ontario.images.config.constants.AuthoritiesConstants;
import org.ontario.images.exceptions.security.EmailAlreadyUsedException;
import org.ontario.images.exceptions.security.InvalidPasswordException;
import org.ontario.images.model.User;
import org.ontario.images.model.projections.ReadableUser;
import org.ontario.images.model.projections.UpdatableUser;
import org.ontario.images.repositories.AuthorityRepository;
import org.ontario.images.repositories.UserRepository;
import org.ontario.images.services.UserService;
import org.ontario.images.utils.RandomUtil;
import org.ontario.images.utils.SecurityUtils;
import org.ontario.images.web.api.request.NewUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            AuthorityRepository authorityRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(NewUserRequest request, boolean isActivated) {

        if (!SecurityUtils.checkPasswordLength(request.getPassword())) {
            throw new InvalidPasswordException();
        }

        this.userRepository.findOneByEmailIgnoreCase(request.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyUsedException();
                });

        final User user = new User();

        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        this.authorityRepository.findByName(AuthoritiesConstants.ROLE_USER)
                .ifPresent(user::addAuthority);

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            final String password = RandomUtil.generatePassword();
            final String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
        } else {
            final String encryptedPassword = this.passwordEncoder.encode(request.getPassword());
            user.setPassword(encryptedPassword);
        }

        if (isActivated) {
            user.setActivated(true);
        } else {
            user.setActivated(false);
            user.setActivationKey(RandomUtil.generateActivationKey());
        }

        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(UpdatableUser user) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public Optional<UpdatableUser> loadUpdatableUserById(Long userId) {
        return Optional.empty();
    }

    @Override
    public Optional<ReadableUser> loadUserById(Long userId) {
        return Optional.empty();
    }

    @Override
    public User loadUserByEmail(String email) {
        return null;
    }

    @Override
    public List<ReadableUser> loadUsersByAuthority(String role) {
        return null;
    }

    @Override
    public void changePassword(User user, String newPassword) {

    }

    @Override
    public Optional<ReadableUser> loadReadableByEmail(String email) {
        return Optional.empty();
    }
}
