package org.ontario.images.web.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ontario.images.model.User;
import org.ontario.images.services.UserService;
import org.ontario.images.web.api.request.NewUserRequest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.ontario.images.factory.UsersFactory.createNewUserReq;
import static org.ontario.images.factory.UsersFactory.createNotActivatedUserEntity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        UserController controller = new UserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldReturnSignInForm() throws Exception {

        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("newUser"))
                .andExpect(view().name("user/signup"));
    }

    @Test
    public void shouldSaveNewUser() throws Exception {
        final NewUserRequest userReq = createNewUserReq();
        final User user = createNotActivatedUserEntity();
        final boolean isActivated = false;

        when(this.userService.createUser(any(NewUserRequest.class), eq(isActivated))).thenReturn(user);

        mockMvc.perform(post("/user/new")
                .param("firstName", userReq.getFirstName())
                .param("lastName", userReq.getLastName())
                .param("email", userReq.getEmail())
                .param("password", userReq.getPassword())
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/update/1"));
    }

    @Test
    public void shouldReturnEntityForUpdate() throws Exception {
        final User user = createNotActivatedUserEntity();
        Optional<User> optional = Optional.of(user);

        when(this.userService.loadUserById(anyLong())).thenReturn(optional);

        mockMvc.perform(get("/user/update/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/update"));
    }
}