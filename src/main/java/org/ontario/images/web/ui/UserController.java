package org.ontario.images.web.ui;

import org.ontario.images.web.api.request.NewUserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/signup")
    public String signupAction(Model model) {
        model.addAttribute("newUser", new NewUserRequest());
        return "user/signup";
    }

    @PostMapping("/user/new")
    public String createUserAction() {
        return null;
    }
}
