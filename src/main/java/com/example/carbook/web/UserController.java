package com.example.carbook.web;

import com.example.carbook.model.dto.UserLoginBindingModel;
import com.example.carbook.model.dto.UserRegisterBindingModel;
import com.example.carbook.model.entity.UserEntity;
import com.example.carbook.service.RoleService;
import com.example.carbook.service.UserService;
import com.example.carbook.service.impl.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;

    private final RoleService roleService;

    public UserController(UserService userService, LoggedUser loggedUser, RoleService roleService) {
        this.userService = userService;
        this.loggedUser = loggedUser;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String login() {
        //TODO: implement better logic
//        if (loggedUser.isLogged()) {
//            return new ModelAndView("redirect:/home");
//        }
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

//    @PostMapping("/login")
//
//        public ModelAndView login(@ModelAttribute("userLoginBindingModel") @Valid UserLoginBindingModel userLoginBindingModel,
//                                  BindingResult bindingResult) {
//
//        //TODO: implement better logic
//        if (loggedUser.isLogged()) {
//            return new ModelAndView("redirect:/home");
//        }
//
//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("login");
//        }
//        boolean hasSuccessfulLogin = userService.login(userLoginBindingModel);
//
//        if (!hasSuccessfulLogin) {
//            ModelAndView modelAndView = new ModelAndView("login");
//            modelAndView.addObject("hasLoginError", true);
//            return modelAndView;
//        }
//
//        return new ModelAndView("redirect:/");
//
//    }


    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel) {
        //TODO: implement better logic
        if (loggedUser.isLogged()) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterBindingModel")
                                     @Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {

        //TODO: implement better logic
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
//    @PostMapping("/logout")
//    public ModelAndView logout() {
//
//        if (!loggedUser.isLogged()) {
//            //!!!!!!!!!!!!!!!CHECK
//            return new ModelAndView("redirect:/home");
//        }
//        this.userService.logout();
//
//        return new ModelAndView("redirect:/");
//    }
    @GetMapping("/admin-panel")
    public String showAdminPanel(Model model) {
        List<UserEntity> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin-panel";
    }

//    @PostMapping("/make-admin")
//    public String makeAdmin(@RequestParam String username, Model model) {
//        if (!userService.isAdmin(username)) {
//            userService.makeAdmin(username);
//            return "redirect:/admin-panel";
//        } else {
//            model.addAttribute("makeAdminError", "User is already an admin");
//            List<UserEntity> users = userService.getAllUsers();
//            model.addAttribute("users", users);
//        }
//        return "admin-panel";
//    }
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
//    @PostMapping("/make-admin")
//    @PreAuthorize("hasRole('ROLE_ADMIN')") // Ensure the user has the 'ROLE_ADMIN' authority
//    public ResponseEntity<?> makeAdmin(@RequestBody UsernameRequest request) {
//        String username = request.getUsername();
//        Map<String, String> response = new HashMap<>();
//
//        try {
//            if (!userService.isAdmin(username)) {
//                userService.makeAdmin(username);
//                response.put("success", "true");
//                response.put("message", "User has become admin");
//                return ResponseEntity.ok(response);
//            } else {
//                response.put("success", "false");
//                response.put("message", "User is already an admin");
//                return ResponseEntity.badRequest().body(response);
//            }
//        } catch (Exception e) {
//            response.put("success", "false");
//            response.put("message", "Failed to make user admin");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//        @PostMapping("/make-admin")
//    public String makeAdmin(@RequestParam String username, Model model) {
//        if (!userService.isAdmin(username)) {
//            userService.makeAdmin(username);
//            return "redirect:/admin-panel";
//        } else {
//            model.addAttribute("makeAdminError", "User is already an admin");
//            List<UserEntity> users = userService.getAllUsers();
//            model.addAttribute("users", users);
//        }
//        return "admin-panel";
//    }
//@PostMapping("/make-admin")
//public String makeAdmin(@RequestParam String username, Model model) {
//    try {
//        System.out.println("Before making user admin: " + username);
//
//        if (!userService.isAdmin(username)) {
//            userService.makeAdmin(username);
//            System.out.println("User successfully made admin: " + username);
//            return "redirect:/admin-panel";
//        } else {
//            model.addAttribute("makeAdminError", "User is already an admin");
//            List<UserEntity> users = userService.getAllUsers();
//            model.addAttribute("users", users);
//        }
//    } catch (Exception e) {
//        System.err.println("Error making user admin: " + e.getMessage());
//        e.printStackTrace();  // Print the full stack trace for detailed information
//        model.addAttribute("makeAdminError", "Failed to make user admin");
//    }
//    return "admin-panel";
//}
@PostMapping("/make-admin")
public ResponseEntity<Map<String, String>> makeAdmin(@RequestBody Map<String, String> requestBody) {
    try {
        String username = requestBody.get("username");
        System.out.println("Before making user admin: " + username);

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
    //    @PostMapping("/make-admin")
//    public String makeAdmin(@RequestParam String username, Model model) {
//        if (!userService.isAdmin(username)) {
//            userService.makeAdmin(username);
//            return "redirect:/admin-panel";
//        } else {
//            model.addAttribute("makeAdminError", "User is already an admin");
//            List<UserEntity> users = userService.getAllUsers();
//            model.addAttribute("users", users);
//        }
//        return "admin-panel";
//    }

}

