package lt.sebpra.demoblog.controllers;

import lt.sebpra.demoblog.models.User;
import lt.sebpra.demoblog.servicies.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/users/new")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);

        return "redirect:/";
    }

    @GetMapping("/user/login")
    public String getLoginForm(Model model){
        model.addAttribute("user", new User());
        return "forms/login-form";
    }

}
