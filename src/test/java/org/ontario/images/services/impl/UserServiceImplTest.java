package org.ontario.images.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ontario.images.factory.AuthoritiesFactory;
import org.ontario.images.model.User;
import org.ontario.images.repositories.AuthorityRepository;
import org.ontario.images.repositories.UserRepository;
import org.ontario.images.services.UserService;
import org.ontario.images.web.api.request.NewUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.ontario.images.config.constants.AuthoritiesConstants.ROLE_USER;
import static org.ontario.images.factory.UsersFactory.*;

public class UserServiceImplTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityRepository authorityRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.userService = new UserServiceImpl(
                this.userRepository,
                this.authorityRepository,
                this.passwordEncoder
        );
    }

    @Test
    public void shouldReturnUserAfterSuccesfulRegistration() {

        final NewUserRequest userRequest = createNewUserReq();

        final String encodedPassword = "has-password";
        when(this.passwordEncoder.encode(any())).thenReturn(encodedPassword);

        when(this.authorityRepository.findByName(eq(ROLE_USER)))
                .thenReturn(Optional.of(AuthoritiesFactory.USER_AUTHORITY));

        doAnswer(AdditionalAnswers.returnsFirstArg()).when(this.userRepository)
                .save(any(User.class));

        final User user = this.userService.createUser(userRequest, false);

        assertThat(user.getEmail()).isEqualTo(USER_EMAIL);
        assertThat(user.getPassword()).isEqualTo(encodedPassword);
        assertThat(user.getFirstName()).isEqualTo(USER_FIRSTNAME);
        assertThat(user.getLastName()).isEqualTo(USER_LASTNAME);

        assertThat(user.getAuthorities())
                .hasSize(1)
                .containsExactlyInAnyOrder(AuthoritiesFactory.USER_AUTHORITY);
    }
}