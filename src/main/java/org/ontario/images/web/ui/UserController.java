package org.ontario.images.web.ui;

import org.ontario.images.exceptions.InternalServerException;
import org.ontario.images.model.User;
import org.ontario.images.services.EmailService;
import org.ontario.images.services.UserService;
import org.ontario.images.web.api.request.NewUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;
    private EmailService emailService;

    @Autowired
    public UserController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @GetMapping("/signup")
    public String signupAction(Model model) {
        model.addAttribute("newUser", new NewUserRequest());
        return "user/signup";
    }

    @PostMapping("/user/new")
    public String createUserAction(
            final Model model,
            final @Valid @ModelAttribute("newUser") NewUserRequest request,
            final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", request);
            return "user/signup";
        }

        final boolean isActivated = false;
        final User user = this.userService.createUser(request, isActivated);

        if (user == null) {
            throw new InternalServerException("We can't create new user");
        }
        emailService.sendActivationEmail(user);

        return "redirect:/user/update/" + user.getId();
    }

    @GetMapping("/user/update/{id}")
    public String updateAction(@PathVariable("id") String id, Model model) {
        Optional<User> optional = userService.loadUserById(Long.valueOf(id));

        if (! optional.isPresent()) {
            throw new InternalServerException("Can't load user with id: " + id);
        }
        model.addAttribute("user", optional.get());

        return "user/update";
    }

    @GetMapping("/user/activate/{id}/{activationKey}")
    public String activeAccountAction(
            @PathVariable("id") String id,
            @PathVariable("activationKey") String activationKey
    ) {
        Optional<User> optional = userService.loadUserById(Long.valueOf(id));

        User user = optional.orElseThrow(() -> new InternalServerException("Can't load user with id: " + id));
        if (user.getActivationKey().equals(activationKey)) {
            user.setActivated(true);
            userService.updateUser(user);
        }

        return "forward:/login";
    }
}
