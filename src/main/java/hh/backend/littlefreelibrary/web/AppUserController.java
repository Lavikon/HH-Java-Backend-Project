package hh.backend.littlefreelibrary.web;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.littlefreelibrary.domain.AppUser;
import hh.backend.littlefreelibrary.domain.AppUserRepository;
import jakarta.validation.Valid;

@Controller
public class AppUserController {

    // Database repository
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // CRUD operations
    // display user list
    @GetMapping("/users/list")
    public String listUsers(Model model) {
        model.addAttribute("users", appUserRepository.findAll());
        return "userlist";
    }

    // add a user form
    @GetMapping("/users/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "userform";
    }

    // Save new user
    @PostMapping("/users/save")
    public String saveUser(@Valid AppUser appUser, BindingResult result) {
        if (result.hasErrors()) {
            return "userform";
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
        return "redirect:/users/list";
    }

    // Delete user
    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") Integer id) {
        appUserRepository.deleteById(id);
        return "redirect:/users/list";
    }

    // Edit user form
    @GetMapping("/users/{id}/edit")
    public String showEditUserForm(@PathVariable("id") Integer id, Model model) {
        AppUser appUser = appUserRepository.findById(id).orElse(null);
        // If user not found, redirect to list
        if (appUser == null) {
            return "redirect:/users/list";
        }
        model.addAttribute("appUser", appUser);
        return "userform";
    }

}
