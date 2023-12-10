package com.example.carbook.web;

import com.example.carbook.model.dto.UserRegisterBindingModel;
import com.example.carbook.model.entity.UserEntity;
import com.example.carbook.service.UserService;
import com.example.carbook.service.impl.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;


    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login-error")
    public String onFailure(
            @ModelAttribute("username") String username,
            Model model) {

        model.addAttribute("username", username);
        model.addAttribute("bad_credentials", "true");

        return "login";
    }
    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel) {
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterBindingModel")
                                     @Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {

        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }
         boolean hasSuccessfulRegistration = userService.register(userRegisterBindingModel);

        if (!hasSuccessfulRegistration) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("hasRegistrationError", true);
            return modelAndView;
        }


        return new ModelAndView("redirect:/login");
    }
    @GetMapping("/admin-panel")
    public String showAdminPanel(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin-panel";
    }

    @PostMapping("/remove-admin")
    public String removeAdmin(@RequestParam String username, Model model) {
        if (userService.isAdmin(username)) {
            userService.removeAdmin(username);
            return "redirect:/admin-panel";
        } else {
           model.addAttribute("removeAdminError", "User is not an admin");
            List<UserEntity> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "admin-panel";
        }
    }
@PostMapping("/make-admin")
public ResponseEntity<Map<String, String>> makeAdmin(@RequestBody Map<String, String> requestBody) {
    try {
        String username = requestBody.get("username");

        if (!userService.isAdmin(username)) {
            userService.makeAdmin(username);
            System.out.println("User successfully made admin: " + username);
            return ResponseEntity.ok(Collections.singletonMap("success", "true"));
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("success", "false"));
        }
    } catch (Exception e) {
        System.err.println("Error making user admin: " + e.getMessage());
        e.printStackTrace();  // Print the full stack trace for detailed information
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("success", "false"));
        }
    }
}

