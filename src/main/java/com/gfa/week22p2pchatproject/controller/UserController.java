package com.gfa.week22p2pchatproject.controller;

import com.gfa.week22p2pchatproject.exception.LoginUnsuccessfulException;
import com.gfa.week22p2pchatproject.exception.NameAlreadyTakenException;
import com.gfa.week22p2pchatproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String generateRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegistrationForm(
            @RequestParam String login,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) throws NameAlreadyTakenException {
        userService.registerUser(login, password);
        redirectAttributes.addFlashAttribute("success", "Your registration was successful. " +
                "Now you can log into the Chat Project.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String generateLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLoginForm(
            @RequestParam String login,
            @RequestParam String password) throws LoginUnsuccessfulException {
        var user = userService.loginUser(login, password);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutUser(RedirectAttributes redirectAttributes) {
        if (userService.logout()) {
            redirectAttributes.addFlashAttribute("success", "You have successfully logged out.");
            return "redirect:/login";
        }
        return "redirect:/";
    }

    @GetMapping("/change")
    public String generateChangeForm(Model model) {
        model.addAttribute("userIdDto", userService.getLoggedUser());
        return "user";
    }

    @PostMapping("/change")
    public String handleChangeForm(@RequestParam String username,
                                   @RequestParam(required = false, defaultValue = "") String avatarUrl) {
        userService.changeLoggedUser(username, avatarUrl);
        return "redirect:/";
    }
}
